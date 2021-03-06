package com.niit.shoppingcart.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.Cart;



	@Repository(value="cartDAO")
	public class CartDAOImpl implements CartDAO{
    	
    	public static final Logger log=LoggerFactory.getLogger(CartDAOImpl.class);
    	
    	
		@Autowired
		private SessionFactory sessionFactory;
		
		public CartDAOImpl(SessionFactory sessionFactory) {
			log.info("cart session===========================================================================================");
			this.sessionFactory=sessionFactory;
		}
		
		@Transactional
		public boolean save(Cart cart) {
			log.info("cart save operation started");
			try {
				sessionFactory.getCurrentSession().save(cart);
				return true;
			} catch (Exception e) {
				
				e.printStackTrace();
				log.info("cart saved");
				return false;
			}
			
		}
		@Transactional
		public boolean update(Cart cart) {
			try {
				sessionFactory.getCurrentSession().update(cart);
				return true;
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
		}

		@Transactional
		public boolean delete(Cart cart) {
			try {
				sessionFactory.getCurrentSession().delete(cart);
				return true;
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}

		}
@Transactional
		@SuppressWarnings({ "unchecked", "deprecation" })
		public List<Cart> list() {
			List<Cart> listCart = (List<Cart>)sessionFactory.getCurrentSession()
							.createCriteria(Cart.class)
							.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

					return listCart;
				}

		@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
		@Transactional
		public Cart get(int id) {
			String hql = "from Cart where id= "+ "'"+ id+"'" ;
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			List<Cart>list= query.list();
			
			if(list==null)
			{
				return null;
			}
			else
			{
				return list.get(0);
			}
		}
		/*@Transactional
		@SuppressWarnings("unchecked")
		public Cart getproduct(int id) {
			String hql="from Cart where productid= "+id;
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			List<Cart>listproduct=query.list();
			//List<Cart> listproduct = (List<Cart>) query.list();
			return listproduct.get(0);
		}*/

		@Transactional
		@SuppressWarnings({ "unchecked", "deprecation" })
		public Cart getproduct(int productid) {
			String hql="from Cart where productid= "+productid;
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			List<Cart>listproduct=query.list();

			if(listproduct.isEmpty())
			{
				
				return null;
			}
			else
			{
				System.out.println("product");
				return listproduct.get(0);
			}
		}
		/*@Transactional
		public void pay(int userId) {
			String hql="update Cart set status='P' where userid="+userId;	
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
		}
		*/
		/*
		@SuppressWarnings("deprecation")
		@Transactional
		public double CartPrice(int userId) {
			Criteria c=sessionFactory.getCurrentSession().createCriteria(Cart.class);
			c.add(Restrictions.eq("userid", userId));
			c.add(Restrictions.eq("status","C"));
			c.setProjection(Projections.sum("price"));
			Double l= (Double)c.uniqueResult();
			return l;
		}
		@SuppressWarnings("deprecation")
		@Transactional
		public long cartsize(int userId) {
			Criteria c=sessionFactory.getCurrentSession().createCriteria(Cart.class);
			c.add(Restrictions.eq("userid", userId));
			c.add(Restrictions.eq("status","C"));
			c.setProjection(Projections.count("userid"));
			long count=(long) c.uniqueResult();
			return count;
		}
		
*/	}
