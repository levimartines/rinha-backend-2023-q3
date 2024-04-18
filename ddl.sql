SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', 'public', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

DROP TABLE IF EXISTS public.PESSOAS;

CREATE TABLE public.PESSOAS(
    ID         uuid               NOT NULL,
    APELIDO    VARCHAR(32) UNIQUE NOT NULL,
    NASCIMENTO VARCHAR(12)        NOT NULL,
    NOME       VARCHAR(100)       NOT NULL,
    STACK      VARCHAR(255),
    PRIMARY KEY (ID),
    BUSCA_TRGM TEXT GENERATED ALWAYS AS (NOME || ' ' || APELIDO || ' ' || COALESCE(STACK, '')) STORED NOT NULL
);
CREATE EXTENSION IF NOT EXISTS pg_trgm;

SELECT pg_catalog.set_config('search_path', 'public', false);

CREATE INDEX CONCURRENTLY IF NOT EXISTS IDX_PESSOA_BUSCA_TGRM
    ON public.PESSOAS USING GIST (BUSCA_TRGM GIST_TRGM_OPS(siglen=256))
    INCLUDE(apelido, nascimento, nome, ID, stack);
