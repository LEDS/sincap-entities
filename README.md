# Sincap
## Introdução
O SinCap é um sistema de informação que permitirá o CNCDO/ES (Centro de Notificação, Captação e Distribuição de Órgãos) controlar algumas atividades do processo de doação e captação de córnea e, também, auxiliará na gestão da informação. Para atingir tais objetivos o sistema de informação, o SinCap, será o mediador das comunicações entre CIHDOTT (Comissão Intra-Hospitalar de Doação de Órgão, Tecido e Transplantes), CNCDO/ES e Banco de Olhos, de modo a dar agilidade e eficiência ao processo de doação de córnea. 

Através do SinCap, o fluxo de informação, que compreende desde a notificação do óbito até o registro da captação da córnea, será gerenciado pela equipe do CNCDO/ES, de uma maneira simples e rápida. Através da automatização proporcionada pelo SinCap o CNCDO/ES pretende diminuir o tempo de execução do processo de doação e captação de córnea e minimizar a quantidades de erros de dados que circulam no processo de forma significativa.

Para maiores informações entre no [link](http://leds.sr.ifes.edu.br/portfolio/sincap/)

## O que é o Sincap Entities?
Sincap Entities é o projeto responsável por conter toda as regras de negócios sobre o projeto Sincap. Esse é a base dos projetos:
* [__Sincap Web__](https://github.com/LEDS/sincap-interface-web/): Interface Web do projeto sincap;
* [__Sincap Web-Service__](https://github.com/LEDS/sincap-webservice): Web Service para integração com o projeto Mobile
* [__Sincap Mobile__](https://github.com/LEDS/sincap-mobile): Versão mobile do projeto sincap contendo as funcionalidades para facilitar a notificação de captação de córnea;

Execute os seguintes passos para a instalação do sincap no servidor:

1) Execute o Script SQL chamado backup.sql que consta na pasta backup.
2) Compile o Sincap Entities com o seguinte comando: mvn clean install.
3) Gerer o Sincap.War com o seguinte comando no projeto Sincap We: mvn clean install.
4) Copie o arquivo Sincap.war para o Tomcat.

