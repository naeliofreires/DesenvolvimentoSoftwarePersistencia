**Testando JDBC** <br>

        EmployeesJDBC empljdbc = new EmployeesJDBC();
        Employees empl = new Employees();

//      Contato 1 <br>

        empl.setName("A");
        empl.setCpf("1");
        empl.setMatriculation("1");
        empl.setEmail("1");

        empljdbc.save(empl);

//      Contato 2 <br>

        empl.setName("B");
        empl.setCpf("2");
        empl.setMatriculation("2");
        empl.setEmail("2");

        empljdbc.save(empl);

//      Contato 3 <br>

        empl.setName("C");
        empl.setCpf("3");
        empl.setMatriculation("3");
        empl.setEmail("3");

        empljdbc.save(empl);
        
//      Deletando Contado 2 <br>

        empljdbc.delete(2);

//      Atualizando Contato 3 <br>

        empl.setId(3);
        empl.setName("CC");
        empl.setCpf("33");
        empl.setMatriculation("33");
        empl.setEmail("33");

        empljdbc.save(empl);
        
 //      getAll <br>
 
         ArrayList<Employees> l = (ArrayList<Employees>) empljdbc.find();
 
         for ( Employees x: l ) 
            System.out.println(x.getName());
 
 //      Buscando por CPF [ CPF é unico] <br>
 
         empl = empljdbc.findByCpf("33");
         System.out.println(empl.getName() + " " + empl.getCpf());
         
 //        Buscando por Nome <br>
 
         ArrayList<Employees> ll = (ArrayList<Employees>) empljdbc.findByNome("A");
 
         for (Employees e: ll) 
             System.out.println(e.getName());
            
**Testando JPA** <br>

//      Inicianlizando 

        JPAUtil.init("devPostgreSQL");
        
//      Inserindo Dados        
        
        EmployeesDAO employeesDAO = new EmployeesJPADAO(Employees.class);
        try {
            employeesDAO.beginTransaction();
            employeesDAO.save(new Employees("A","A","A","A","A"));
            employeesDAO.save(new Employees("B","B","B","B","B"));
            employeesDAO.save(new Employees("C","C","C","C","C"));
            employeesDAO.save(new Employees("D","D","D","D","D"));
            employeesDAO.save(new Employees("E","E","E","E","E"));
            employeesDAO.commit();
        }catch (Exception ex) {
            employeesDAO.rollback();
            ex.printStackTrace();
        }finally {
            employeesDAO.close();
        }
        
//      Pegando Todos 

        List<Employees> l = employeesDAO.find(); 
        
        for(Employees e : l)
            System.out.println("Nome: " + e.getName());
            
        employeesDAO.close();
     
     
 //      Pegando Employees com ID = 2
 
         Employees l = employeesDAO.find(2);
         
         System.out.println("Nome: " + l.getName());
         
         employeesDAO.close();
         
 //      Removendo Employee com ID = 3
 
         employeesDAO.delete(new Employees(1));