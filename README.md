# HomerBank

Para execução deste projeto é necessária: 

- Java JDK 1.8 +
- Utilização da IDE NetBeans ou a ferramenta de linha de comando Apache ANT devidamente configurado
- Banco de dados MySQL ou Oracle instalados com as seguintes credenciais:
	- Oracle:: User: "tor", Password: "tor" 
	- MySQL:: User: "root", Password: "root"

- Os respectivos scripts para criação do banco e população das tabelas estão no mesmo diretório que este tutorial
- Para alterar o banco de dados no projeto, é necessário alterar a constante BD da classe br.com.homerbank.util.Configs. As duas opções aceitas são:
	- "oracle" ou "mysql"
