CREATE DATABASE devsu_bank;
GO

USE devsu_bank;
GO


BEGIN TRY
    BEGIN TRANSACTION

        CREATE TABLE Configuration
        (
            configuration_id TINYINT      NOT NULL IDENTITY (1, 1),

            code             VARCHAR(20)  NOT NULL,
            config_value     VARCHAR(255) NOT NULL,
            description      VARCHAR(255) NOT NULL,

            CONSTRAINT PK_Configuration PRIMARY KEY CLUSTERED (configuration_id),
            CONSTRAINT UQ_Configuration_AppCompCod UNIQUE (code),
        );

        INSERT INTO Configuration (code, config_value, description)
        VALUES ('limit.daily.debit', '1000', 'Max. amount allow to debit daily');


        CREATE TABLE Genre
        (
            genre_id    TINYINT     NOT NULL IDENTITY (1, 1),
            description VARCHAR(25) NOT NULL,
            mnemonic    VARCHAR(5)  NOT NULL,

            CONSTRAINT PK_Genre PRIMARY KEY CLUSTERED (genre_id),
            CONSTRAINT UQ_Genre_Description UNIQUE (description),
            CONSTRAINT UQ_Genre_Mnemonic UNIQUE (mnemonic)
        );

        INSERT INTO Genre(description, mnemonic)
        VALUES ('Masculino', 'M'),
               ('Femenino', 'F'),
               ('Prefiero no decirlo', 'PNS');


        CREATE TABLE AccountType
        (
            account_type_id TINYINT NOT NULL IDENTITY (1, 1),
            description     VARCHAR(15),
            mnemonic        VARCHAR(5),

            CONSTRAINT PK_AccountType PRIMARY KEY CLUSTERED (account_type_id),
            CONSTRAINT UQ_AccountType_Description UNIQUE (description),
            CONSTRAINT UQ_AccountType_Mnemonic UNIQUE (mnemonic)
        );

        INSERT INTO AccountType (description, mnemonic) VALUES ('Corriente', 'CORR'), ('Ahorros', 'AHORR');


        CREATE TABLE MovementType
        (
            movement_type_id TINYINT NOT NULL IDENTITY (1, 1),
            description      VARCHAR(15),
            mnemonic         VARCHAR(5),

            CONSTRAINT PK_MovementType PRIMARY KEY CLUSTERED (movement_type_id),
            CONSTRAINT UQ_MovementType_Description UNIQUE (description)
        );

        INSERT INTO MovementType (description, mnemonic) VALUES (N'Crédito', 'CRE'), (N'Débito', 'DEB');

        CREATE TABLE Person
        (
            person_id      UNIQUEIDENTIFIER NOT NULL,
            name           VARCHAR(200)     NOT NULL,
            genre_id       TINYINT          NOT NULL,
            age            TINYINT          NOT NULL,
            identification VARCHAR(13)      NOT NULL,
            address        VARCHAR(500)     NOT NULL,
            phone          CHAR(10)         NOT NULL,

            CONSTRAINT PK_Person PRIMARY KEY CLUSTERED (person_id),
            CONSTRAINT FK_Person_Genre FOREIGN KEY (genre_id) REFERENCES Genre (genre_id) ON UPDATE CASCADE,
            CONSTRAINT UQ_Person_Identification UNIQUE (identification),
        );

        CREATE TABLE Client
        (
            client_id UNIQUEIDENTIFIER NOT NULL,
            password  VARCHAR(255)     NOT NULL,
            status    BIT              NOT NULL DEFAULT 1,
            person_id UNIQUEIDENTIFIER NOT NULL,

            CONSTRAINT PK_Client PRIMARY KEY CLUSTERED (client_id),
            CONSTRAINT FK_Client_Person FOREIGN KEY (person_id) REFERENCES Person (person_id) ON UPDATE CASCADE ON DELETE CASCADE
        );

        CREATE TABLE Account
        (
            account_id      UNIQUEIDENTIFIER NOT NULL,
            account_number  VARCHAR(50)      NOT NULL,
            account_type_id TINYINT          NOT NULL,
            amount          MONEY            NOT NULL DEFAULT 0,
            status          BIT              NOT NULL DEFAULT 1,
            client_id       UNIQUEIDENTIFIER NOT NULL,

            CONSTRAINT PK_Account PRIMARY KEY CLUSTERED (account_id),
            CONSTRAINT FK_Account_Client FOREIGN KEY (client_id) REFERENCES Client (client_id) ON UPDATE CASCADE ON DELETE CASCADE,
            CONSTRAINT FK_Account_Type FOREIGN KEY (account_type_id) REFERENCES AccountType (account_type_id) ON UPDATE CASCADE,
        );

        CREATE TABLE Movement
        (
            movement_id      UNIQUEIDENTIFIER NOT NULL,

            created_at       DATETIME         NOT NULL DEFAULT GETDATE(),
            movement_type_id TINYINT          NOT NULL,
            amount           MONEY            NOT NULL,
            amount_initial   MONEY            NOT NULL,
            amount_balance   MONEY            NOT NULL,
            account_id       UNIQUEIDENTIFIER NOT NULL,

            CONSTRAINT PK_Movement PRIMARY KEY CLUSTERED (movement_id),
            CONSTRAINT FK_Movement_Type FOREIGN KEY (movement_type_id) REFERENCES MovementType (movement_type_id) ON UPDATE CASCADE,
            CONSTRAINT FK_Movement_Account FOREIGN KEY (account_id) REFERENCES Account (account_id) ON UPDATE CASCADE ON DELETE CASCADE,
        );

    COMMIT TRANSACTION
    PRINT 'Tablas creadas correctamente';
END TRY
BEGIN CATCH
    ROLLBACK TRANSACTION
    PRINT 'Erro al crear las tablas: ' + ERROR_MESSAGE()
END CATCH
