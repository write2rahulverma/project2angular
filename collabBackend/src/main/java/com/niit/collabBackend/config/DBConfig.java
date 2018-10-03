package com.niit.collabBackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collabBackend.Dao.BlogDao;
import com.niit.collabBackend.Dao.ForumDao;
import com.niit.collabBackend.Dao.JobDao;
import com.niit.collabBackend.Dao.UserDao;
import com.niit.collabBackend.DaoImpl.BlogDaoImpl;
import com.niit.collabBackend.DaoImpl.ForumDaoImpl;
import com.niit.collabBackend.DaoImpl.JobDaoImpl;
import com.niit.collabBackend.DaoImpl.UserDaoImpl;
import com.niit.collabBackend.model.ApplyJob;
import com.niit.collabBackend.model.Blog;
import com.niit.collabBackend.model.Forum;
import com.niit.collabBackend.model.Job;
import com.niit.collabBackend.model.UserDetail;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages= {"com.niit.collabBackend.model"})
public class DBConfig {
	
	
	/// Data Source object
	  
		public DataSource getDataSource() {
			System.out.println("DataSource initialize............!");
			
			DriverManagerDataSource dmds = new DriverManagerDataSource();
			
			dmds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			dmds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			dmds.setUsername("system");
			dmds.setPassword("Akiv@123");
			return dmds;
			
		}

		   // sessionFactory bean
		
		@Bean(name="sessionFactory")
		public SessionFactory getsessionFactory() {
			
			Properties hibernateProp =new Properties();
			System.out.println("hibernate properties object created.........!");
				
					//hibernateProp.put("hibernate.hbm2ddl.auto", "create"); // we can use create ,update ,create drop as value here.
					hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");// for oracle
					hibernateProp.put("hibernate.show_sql","true");			// version 10g,11g,12c
					hibernateProp.put("hibernate.format_sql","true");
					
					System.out.println("Local Session Factory Builder Object Creating........!");
					
					LocalSessionFactoryBuilder builder =new LocalSessionFactoryBuilder(getDataSource());
					builder.addProperties(hibernateProp);
					
					builder.addAnnotatedClass(Blog.class);
					builder.addAnnotatedClass(Forum.class);
					builder.addAnnotatedClass(Job.class);
					builder.addAnnotatedClass(UserDetail.class);
					builder.addAnnotatedClass(ApplyJob.class);
					
					System.out.println("Table Created.............!"); 
					SessionFactory sessionFactory = builder.buildSessionFactory();
					
					return sessionFactory;
		}
		@Bean(name="blogDao")
		public BlogDao getblogDao(SessionFactory sessionFactory) {

			System.out.println("Blog Dao object created");
			return new BlogDaoImpl();
		}
		
		@Bean(name="forumDao")
		public ForumDao getforumDao() {

			System.out.println("Forum Dao object created");
			return new ForumDaoImpl();
		}
		@Bean(name="jobDao")
		public JobDao getJobDao() {

			System.out.println("Job Dao object created");
			return new JobDaoImpl();
		}
		
		
		@Bean(name="userDao")
		public UserDao getuserDao() {

			System.out.println("User Dao object created");
			return new UserDaoImpl();
		
		}
		   // hibernate transaction bean
		@Bean
		public HibernateTransactionManager geHibernateTransactionManager(SessionFactory sessionFactory){
			System.out.println("TMANAGER............!");
			return new HibernateTransactionManager(sessionFactory);
		}
		
	}

