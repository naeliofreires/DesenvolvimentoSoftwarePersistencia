# Desenvolvimento de Software para Persistência

# Lista de Exercícios 07 - Questões

> Naélio Freires Roberto de Souza - Sistemas de Informação <br>
> naeliofreires@gmail.com <br>
> Resolução [ Respostas ] <br>

01 - Ainda com relação a JPA, mostre uma consulta sua da Lista 06 usando JPA e     envolvendo mais de uma tabela, que é transformada em 2 ou mais comandos SQL. Depois, altere a consulta JPA para que ela gere apenas um único comando SQL. Ou seja: o objetivo da questão é a otimização de uma consulta através de JPA.

        public void CriteriaV02(){
                JPAUtil.init("devPostgreSQL");
                EntityManager em = JPAUtil.getEntityManager();

                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Dependents> cq = cb.createQuery(Dependents.class);

                Root<Dependents> r = cq.from(Dependents.class);
                r.fetch("func");

                ParameterExpression<String> p = cb.parameter(String.class, "letter");

                List<Dependents> depts = em.createQuery(cq.where(cb.like(r.get("nome").as(String.class), p)))
                        .setParameter("letter",  "d%")
                        .getResultList();

                JPAUtil.closeEntityManager();
        }

02 -  Dadas as tabelas abaixo no modelo relacional, crie uma única coleção de documentos no MongoDB para representá-las. A coleção deve conter livros com suas respectivas editoras. 

        // JSON LIVROS
    { 
        "isbn":  "213", 
        "titulo": "Banco de Dados", 
        "ano_publicacao": "2011", 
        "qtd_estoque": "2", 
        "valor": "50,00",  
        "editora": {"id": "4","nome": "Novatec"}
    },
    {
        "isbn": "425", 
        "titulo": "Sistemas Operacionais", 
        "ano_publicacao": "2010", 
        "qtd_estoque": "3", 
        "valor": "80,00",  
        "editora": { "id": "3", "nome": "Melhoramentos"}
    },
    {
        "isbn": "732", 
        "titulo": "Lógica de Programação", 
        "ano_publicacao": "2009", 
        "qtd_estoque": "1", 
        "valor": "60,00",  
        "editora": {"id": "2","nome": "FTD"}
    },
    {
        "isbn": "441", 
        "titulo": "Programação Orientada a Objetos", 
        "ano_publicacao": "2012", 
        "qtd_estoque": "1", 
        "valor": "70,00",  
        "editora":  {"id": "1", "nome": "Atica" }
    },
    {
        "isbn": "659", 
        "titulo": "Java para Nerds", 
        "ano_publicacao": "2010", 
        "qtd_estoque": "3", 
        "valor": "90,00",  
        "id_editora": {"id": "4","nome": "Novatec"}
    },
    {
        "isbn": "963", 
        "titulo": "Engenharia de Software", 
        "ano_publicacao": "2010", 
        "qtd_estoque": "2", 
        "valor": "40,00",  
        "editora": {"id": "2","nome": "FTD"}
    },
    {
        "isbn": "376", 
        "titulo": "Redes de Computadores", 
        "ano_publicacao": "2008", 
        "qtd_estoque": "1", 
        "valor": "100,00", 
        "editora": { "id": "3", "nome": "Melhoramentos"}
    }

03 - Faça as seguintes consultas diretamente no MongoDB sobre a coleção criada:
a) Obter os títulos e valores dos livros que possuem a string “Programação” em seu título. 
- db.livros.find({titulo:/Programação/}).pretty();

        {
        	"_id" : ObjectId("59e50c56b3f8e8d2017a378c"),
        	"isbn" : 732,
        	"titulo" : "Lógica de Programação",
        	"o_publicacao" : "2009",
        	"qtd_estoque" : "1",
        	"valor" : "60,00",
        	"id_editora" : 2
        }
        {
        	"_id" : ObjectId("59e50c67b3f8e8d2017a378d"),
        	"isbn" : 441,
        	"titulo" : "Programação Orientada a Objetos",
        	"ano_publicacao" : "2012",
        	"qtd_estoque" : "1",
        	"valor" : "70,00",
        	"id_editora" : 1
        }
b) Obter os títulos e valores dos livros com preços acima de R$ 60,00. Os
resultados devem ser ordenados em ordem decrescente pelo valor do livro. 
- db.livros.find({valor:{$gte: "60,00"}}).sort({valor:-1}).pretty();

        {
        	"_id" : ObjectId("59e50c79b3f8e8d2017a378e"),
        	"isbn" : 659,
        	"titulo" : "Java para Nerds",
        	"o_publicacao" : "2010",
        	"qtd_estoque" : "3",
        	"valor" : "90,00",
        	"id_editora" : 4
        }
        {
        	"_id" : ObjectId("59e50c48b3f8e8d2017a378b"),
        	"isbn" : 425,
        	"titulo" : "Sistemas Operacionais",
        	"o_publicacao" : "2010",
        	"qtd_estoque" : "3",
        	"valor" : "80,00",
        	"id_editora" : 3
        }
        {
        	"_id" : ObjectId("59e50c67b3f8e8d2017a378d"),
        	"isbn" : 441,
        	"titulo" : "Programação Orientada a Objetos",
        	"ano_publicacao" : "2012",
        	"qtd_estoque" : "1",
        	"valor" : "70,00",
        	"id_editora" : 1
        }
        {
        	"_id" : ObjectId("59e50c56b3f8e8d2017a378c"),
        	"isbn" : 732,
        	"titulo" : "Lógica de Programação",
        	"o_publicacao" : "2009",
        	"qtd_estoque" : "1",
        	"valor" : "60,00",
        	"id_editora" : 2
        }

c) Obter os títulos dos livros e os nomes das suas respectivas editoras. Os resultados devem ser exibidos em ordem crescente pelo título do livro. Os livros que não possuem editora também devem aparecer na listagem. 

-   db.livros.find({}, {titulo: 1, editora: 1}).sort({titulo : 1}).pretty();

    
            {
                "_id" : ObjectId("59e544c4efecff6a0c85c35a"),
                "titulo" : "Banco de Dados",
                "editora" : {
                        "id" : "4",
                        "nome" : "Novatec"
                }
            }
            {
                "_id" : ObjectId("59e544c4efecff6a0c85c35f"),
                "titulo" : "Engenharia de Software",
                "editora" : {
                        "id" : "2",
                        "nome" : "FTD"
                }
            }
            {
                "_id" : ObjectId("59e544c4efecff6a0c85c35e"),
                "titulo" : "Java para Nerds"
            }
            {
                "_id" : ObjectId("59e544c4efecff6a0c85c35c"),
                "titulo" : "Lógica de Programação",
                "editora" : {
                        "id" : "2",
                        "nome" : "FTD"
                }
            }
            {
                "_id" : ObjectId("59e544c4efecff6a0c85c35d"),
                "titulo" : "Programação Orientada a Objetos",
                "editora" : {
                        "id" : "1",
                        "nome" : "Atica"
                }
            }
            {
                "_id" : ObjectId("59e544c4efecff6a0c85c360"),
                "titulo" : "Redes de Computadores",
                "editora" : {
                        "id" : "3",
                        "nome" : "Melhoramentos"
                }
            }
            {
                "_id" : ObjectId("59e544c4efecff6a0c85c35b"),
                "titulo" : "Sistemas Operacionais",
                "editora" : {
                        "id" : "3",
                        "nome" : "Melhoramentos"
                }
            }




