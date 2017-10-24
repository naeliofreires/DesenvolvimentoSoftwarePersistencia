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

//      Init

        JPAUtil.init("devPostgreSQL");
        EmployeesDAO employeesDAO = new EmployeesJPADAO(Employees.class);

//      Add Employees

        try {
            employeesDAO.beginTransaction();
            employeesDAO.save(new Employees("01","11","f1","@1","10"));
            employeesDAO.save(new Employees("02","22","f2","@2","20"));
            employeesDAO.save(new Employees("03","33","f3","@3","30"));
            employeesDAO.save(new Employees("04","44","f4","@4","40"));
            employeesDAO.save(new Employees("05","55","f5","@5","50"));
            employeesDAO.save(new Employees("06","66","f6","@6","60"));
            employeesDAO.commit();
        }catch (Exception ex) {
            employeesDAO.rollback();
            ex.printStackTrace();
        }finally {
            employeesDAO.close();
        }

//      Add Dependents

        DependentsDAO dependentsDAO = new DependentsJPADAO(Dependents.class);

        try {
            dependentsDAO.beginTransaction();
            dependentsDAO.save(new Dependents("010101","dependente01", new Employees(1)));
            dependentsDAO.save(new Dependents("020202","dependente02", new Employees(2)));
            dependentsDAO.save(new Dependents("030303","dependente03", new Employees(3)));
            dependentsDAO.save(new Dependents("040404","dependente04", new Employees(4)));
            dependentsDAO.save(new Dependents("050505","dependente05", new Employees(5)));
            dependentsDAO.save(new Dependents("060606","dependente06", new Employees(6)));
            dependentsDAO.commit();
        }catch (Exception ex){
            dependentsDAO.rollback();
            ex.printStackTrace();
        }finally {
            dependentsDAO.close();
        }
        
//      Get All Employees

        List<Employees> l = employeesDAO.find(); 
        
        for(Employees e : l)
            System.out.println("Nome: " + e.getName());
            
        employeesDAO.close();
     
     
 //      Get Employees With ID = 2
 
         Employees l = employeesDAO.find(2);
         
         System.out.println("Nome: " + l.getName());
         
         employeesDAO.close();
         
 //      Remove Employee With ID = 3
 
         employeesDAO.delete(new Employees(1));
 
 // Consultas de Vários Modelos
 
        EmployeeServiceBean employeeServiceBean = new EmployeeServiceBean();
        List<Employees> l = new List<Employees>();
        
 //Named Query
 
            List<Employees> l = employeeServiceBean.NamedQueryGetAll();
            System.out.println("Consulta feita com Named Query");
            for(Employees x: l)
                System.out.println("Name: " + x.getName() + " CPF: " + x.getCpf());
 
 //JQPL
 
            l = employeeServiceBean.JQPLGetAll();
            System.out.println("Consulta feita com JPQL");
            for(Employees x: l)
                System.out.println("Name: " + x.getName() + " CPF: " + x.getCpf());
 
 //Criteria
 
            l = employeeServiceBean.CriteriaGetAll();
            System.out.println("Consulta feita com Criteria Query");
            for(Employees x: l)
                System.out.println("Name: " + x.getName() + " CPF: " + x.getCpf());
 
 //SQL Native
 
            l = employeeServiceBean.SQLNativeGetAll();
            System.out.println("Consulta feita com SQL Native");
            for(Employees x: l)
                System.out.println("Name: " + x.getName() + " CPF: " + x.getCpf());
              
              
 // Consultas para Dependents com varios modelos.
      
         List<Dependents> l = null;
         DependentsServiceBean dependentsServiceBean = new DependentsServiceBean();

//  JPQL

         l = dependentsServiceBean.JPQLNameLike();
           
// SQL NATIVE         
 
         l = dependentsServiceBean.SQLNative();
            
// QUERY            
        
         l =dependentsServiceBean.Query();
              
// CRITERIA       
        
         l = dependentsServiceBean.Criteria();

// EXIBIÇÃO

         for (Dependents a : l)
             System.out.println("Name:" + a.getName() + " " + "Employees: " + a.getEmpl().getName());