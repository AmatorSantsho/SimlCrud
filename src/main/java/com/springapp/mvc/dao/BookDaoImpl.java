package com.springapp.mvc.dao;

import com.springapp.mvc.model.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by 123 on 03.10.2017.
 */
@Repository
public class BookDaoImpl implements BookDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);

    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);


    }

    @Override
    public void removeBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        if (book != null)
            session.delete(book);

    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        return book;
    }


    @Override
    public List<Book> findByParam(String t, String d, String a, Integer page) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria=session.createCriteria(Book.class);

        if (page == null) {
            if (!t.equals("")) {
               criteria.add(Restrictions.like("title",t));
                return criteria.list();
            }else if (!d.equals("")) {
                criteria.add(Restrictions.like("description",d));
                return criteria.list();

            } else if (!a.equals("")) {
                criteria.add(Restrictions.like("author",a));
                return criteria.list();
            } else {
 return criteria.list();

            }
        } else {
           criteria.setFirstResult((page-1)*10);
            criteria.setMaxResults(10);
            return criteria.list();
        }

    }

    @Override
    public Long countBooks() {
        Session session = this.sessionFactory.getCurrentSession();

       Long result=  ((Long) session.createQuery("select count(*) from Book").uniqueResult());

        return result;
    }
}
