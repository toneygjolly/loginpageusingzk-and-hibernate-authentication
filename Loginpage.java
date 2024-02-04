//	package javapart;
//	
//	
//	
//	import java.util.ArrayList;
//	import java.util.List;
//	
//	
//	import org.hibernate.Session;
//	import org.hibernate.SessionFactory;
//	import org.hibernate.Transaction;
//	import org.hibernate.query.Query;
//	
//	import dataconnect.HibernateConnect;
//	
//	public class Loginpage {
//		 private SessionFactory fact;
//		 private Session ses;
//		 private Transaction tx;
//		 private List<Loginpojo>logindata;
//		 public Loginpage() {
//			 fact = HibernateConnect.getSessionFactory();
//			 logindata= new ArrayList<Loginpojo>();
//		 }
//		 public void insertlogindata() {
//		            ses = fact.openSession();
//		            ses.beginTransaction();
//		            Loginpojo lp=new Loginpojo();
//		            lp.setName("Toney");
//		            lp.setPassword("toney");
//		            ses.save(lp);
//				    ses.getTransaction().commit();
//		            
//		 }
////		 public List<Loginpojo> fetchlogindetails(String username) {
////			 ses = fact.openSession();
////	         ses.beginTransaction();
////	       //  Query query = ses.createQuery("select b.name, b.password from Loginpojo b");
////	         Query query=ses.createQuery("select b from Loginpojo b");
////	         logindata =query.list();
////	         
////	         for(Loginpojo d:logindata) {
////	        	 if(d.getName().contains(username)) {
////	        		 d.getName();
////	        		 logindata.add(d);
////	        	 }
////	        // System.out.println("name"+d.getName());  
////	         
////	         }
////			return logindata;
////		 }
////		
//		 public boolean usernameExists(String username) {
//			    if (username == null || username.isEmpty()) {
//			        return false; // Return false if username is null or empty
//			    }
//			    try (Session ses = fact.openSession()) {
//			        ses.beginTransaction();
//			        Query<Long> query = ses.createQuery("select count(*) from Loginpojo where name = :username", Long.class);
//			        query.setParameter("username", username);
//			        long count = query.uniqueResult();
//			        ses.getTransaction().commit();
//			        return count > 0; // Return true if count is greater than 0, indicating the username exists
//			    } catch (Exception e) {
//			        e.printStackTrace();
//			        return false; // Return false if any exception occurs
//			    }
//			}
//
//		 public static void main(String args []) {
//			 Loginpage p =new Loginpage();
//				// p.insertlogindata();
//				//p.usernameExists(null);
//			 
//		 }
//	}
package javapart;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

import dataconnect.HibernateConnect;

public class Loginpage {
    private SessionFactory fact;

    public Loginpage() {
        fact = HibernateConnect.getSessionFactory();
    }

    public boolean authenticateUser(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false; // Return false if username or password is null or empty
        }

        try (Session ses = fact.openSession()) {
            ses.beginTransaction();
            Query<Loginpojo> query = ses.createQuery("from Loginpojo where name = :username and password = :password", Loginpojo.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<Loginpojo> result = query.list();
            ses.getTransaction().commit();
          // return true;
            return !result.isEmpty(); // Return true if result is not empty, indicating successful authentication
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false if any exception occurs
        }
    }
}
