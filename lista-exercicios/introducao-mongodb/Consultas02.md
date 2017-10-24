Desenvolvimento de Software
========================
##Lista 08

----------

> Naélio Freires R. de Souza naeliofreires@gmail.com Sistemas de
> Informação - 380055

###Questões

**01** Faça as seguintes consultas diretamente no MongoDB sobre a coleção criada na Lista 07:

**a)** Obter o nome da editora, a quantidade total de livros por editora e o valor total (qtd_estoque * valor) dos livros para cada editora. **Somente considerar os livros publicados a partir de 2010.**
```js
db.livros.aggregate([
    {$match:{ano_publicacao: {'$gte':'2010'}}},
    {$group:{
    	_id:{nome:'$editora.nome'},
    	valor_total:{$sum:{$multiply: ['$qtd_estoque','$valor']}},
	qtd_total:{$sum:'$qtd_estoque'}}} 
])
```
**b)** Obter a quantidade total de livros disponíveis em estoque com valor unitário abaixo de R$ 100,00.

```js
db.livros.aggregate([
	{$match:{valor:{$lt:100}}},
	{$group:{
		_id:null,qtd:{$sum:'$qtd_estoque'}
		}
	}
])
```


**02** Crie um novo database no MongoDB com 2 coleções para representar as tabelas da Lista 07. Sugiro que as coleções sejam criadas de modo a permitir o uso de $lookup. Veja detalhes em: https://docs.mongodb.com/manual/reference/operator/aggregation/lookup/

    Faça as seguintes consultas deste novo database: 

**a)** Obter os títulos dos livros e os nomes das suas respectivas editoras. Os resultados devem ser exibidos em ordem crescente pelo título do livro. Os livros que não possuem editora também devem aparecer na listagem.

    	db.livros.aggregate([
        {$match:{ano_publicacao: {'$gte':'2010'}}},
        {$lookup:from:'editoras',localField:'id_editora',foreignField:'id',as:'Editora'}},
        {$group:{_id:'$editora.nome',qtd_total:{$sum:'$qtd_estoque'},valor_total:{$sum:{$multiply:['$qtd_estoque','$valor']}}}}
    ])

**b)** Obter o nome da editora, a quantidade total de livros por editora e o valor total (qtd_estoque * valor) dos livros para cada editora. Somente considerar os livros publicados a partir de 2010.

    db.livros.aggregate([
        {$lookup: from:'editoras',localField:'id_editora',foreignField:'id',as:'Editora'}},
        {$sort:{titulo:1}},
        {$project:{titulo:1,'Editora.nome':1}}
    ])

