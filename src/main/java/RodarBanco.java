/**
 *
 * @author 20102BSI0553
 */
public class RodarBanco {
    public static void main(){
        /*
        POPULAR BANCO PARA TESTES
        
        Rodar arquivo sincapBancoNConsistente.backup
        Dar drop cascade na coluna obito        
        Limpar Construir Sincap        
        INSERT INTO notificador (nome, cpf, usuario_id) values ( 'Marcos' , '111.111.111-11', 1) 
        INSERT INTO notificador_hospital (notificador_id, hospitais_instituicaoid) values (5, 1)
        
        INSERT INTO bairro (nome) values ('Serra Dourada II')
        SELECT * FROM bairro -- id = 1

        INSERT INTO cidade (nome) values ('Serra')
        SELECT * FROM cidade  -- id = 1

        INSERT INTO cidade_bairro (cidade_id, bairros_id) values (1, 1)
        SELECT * FROM cidade_bairro

        INSERT INTO estado (nome, sigla) values ('Espirito Santo', 'ES')
        SELECT * FROM estado -- id = 1

        INSERT INTO estado_cidade (estado_id, cidades_id) values (1,1)
        SELECT * FROM estado_cidade

        INSERT INTO pais (nome) values ('Brasil')
        SELECT * FROM pais -- id = 1

        INSERT INTO pais_estado (pais_id, estados_id) values (1,1)
        SELECT * FROM pais_estado
        */
    }
}
