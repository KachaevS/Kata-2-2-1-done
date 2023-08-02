package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("from User", User.class);
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public User getUserByCarModelAndSeries(String carModel, int carSeries) {
        User user;
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series", User.class);
        query.setParameter("model", carModel);
        query.setParameter("series", carSeries);
        user = query.uniqueResult();
        session.close();
        return user;
    }
}

