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
    public List<Book> findByParam(String title, String description, String author, Integer page) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Book.class);

        if (title != null && !"".equals(title.trim())) {
            criteria.add(Restrictions.like("title", likeValue(title)));
        }

        if (description != null && !"".equals(description.trim())) {
            criteria.add(Restrictions.like("description", likeValue(description)));
        }

        if (author != null && !"".equals(author.trim())) {
            criteria.add(Restrictions.like("author", likeValue(author)));
        }

        if (page == null) {
            page = 1;
        }

        criteria.setFirstResult((page - 1) * 10);
        criteria.setMaxResults(10);
        return criteria.list();


    }

    private String likeValue(String value) {
        return "%" + value + "%";
    }

    @Override
    public Long countBooks() {
        Session session = this.sessionFactory.getCurrentSession();

        Long result=  ((Long) session.createQuery("select count(*) from Book").uniqueResult());

        return result;
    }
}
