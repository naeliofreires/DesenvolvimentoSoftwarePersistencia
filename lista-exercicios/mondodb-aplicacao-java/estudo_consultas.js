// mostra a quantidade de livros que foi publicado por ano
db.livros.aggregate([
    {
        $group: {
            _id: "$ano_publicacao",
            total: { $sum: 1 }
        }

    }
])

// mostrando a media da quantidade de livros publicado agrupado por ano
db.livros.aggregate([
    {

        $group: {
            _id: "$ano_publicacao",
            quantidade: { $sum: "$qtd_estoque" },
            media_qtd: { $avg: "$qtd_estoque" }
        }

    }
])

// aplicando uma condicação de ano de publicação acima de 2010
db.livros.aggregate([
    {
        $match: { ano_publicacao: { $gte: 2010 } }
    },
    {

        $group: {
            _id: "$ano_publicacao",
            quantidade: { $sum: "$qtd_estoque" },
            media_qtd: { $avg: "$qtd_estoque" }
        }

    }
])

// nome da editora, a quantidade total de livros por editora e o valor total (qtd_estoque * valor) 
// dos livros para cada editora. Somente considerar os livros publicados a partir de 2010.
// $editora.nome, pois editora é um objeto incluso dentro de cada livro, não é uma outra collection
db.livros.aggregate([
    {
        $match: { ano_publicacao: { $gte: 2010 } }
    },
    {

        $group: {
            _id: { nome: '$editora.nome' },
            quantidade: { $sum: "$qtd_estoque" },
            valor_total: { $sum: { $multiply: ['$qtd_estoque', '$valor'] } }
        }

    }
])

// Conhecendo o $lookup
// retorna o livro e suas respectivas editoras
db.livros.aggregate([
    {
        $lookup:
        {
            from: "editoras",
            localField: "id_editora",
            foreignField: "id",
            as: "inventory_docs"
        }
    }
]).pretty()

// retornando os livros e suas respectivas por ordem crescente pelo titulo do livro
db.livros.aggregate([
    {
        $sort: { titulo: 1 }
    },
    {
        $lookup:
        {
            from: "editoras",
            localField: "id_editora",
            foreignField: "id",
            as: "inventory_docs"
        }

    }
]).pretty()

// Conhecendo o $project
// Listando os livros, apenas titulo e o valor
db.livros.aggregate([
    {
        $project: {
            _id: 0, titulo: 1, valor: 1
        }
    }
])

// listando os livros e suas respectivias editoras e mostrando apenas o seu titulo
// valor e o nome da editora ultilizando o project para modificar o documento retornado
db.livros.aggregate([
    {
        $lookup:
        {
            from: "editoras",
            localField: "id_editora",
            foreignField: "id",
            as: "editora"
        }

    },
    {
        $project: {
            _id: 0, titulo: 1, valor: 1, "editora.nome": 1
        }
    }
]).pretty()


// Obter os títulos dos livros e os nomes das suas respectivas editoras. Os resultados 
// devem ser exibidos em ordem crescente pelo título do livro. 
// Os livros que não possuem editora também devem aparecer na listagem.
db.livros.aggregate([
    {
        $sort: { titulo: 1 }
    },
    {
        $lookup:
        {
            from: "editoras",
            localField: "id_editora",
            foreignField: "id",
            as: "editora"
        }

    },
    {
        $project: {
            _id: 0, titulo: 1, valor: 1, "editora.nome": 1
        }
    }
]).pretty()


// Obter o nome da editora, a quantidade total de livros por editora e o valor total (qtd_estoque * valor) 
// dos livros para cada editora. Somente considerar os livros publicados a partir de 2010.
db.livros.aggregate([
    {
        $match: { ano_publicacao: { '$gte': 2010 } }
    },
    {
        $lookup:
        {
            from: 'editoras',
            localField: 'id_editora',
            foreignField: 'id',
            as: 'editora'
        }
    },
    {
        $group:
        {
            _id: '$editora.nome',
            qtd_total: { $sum: '$qtd_estoque' },
            valor_total: {
                $sum: { $multiply: ['$qtd_estoque', '$valor'] }
            }
        }
    }    
]).pretty();