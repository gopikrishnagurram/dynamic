package com.wavelabs.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wavelabs.model.Car;
import com.wavelabs.utility.Helper;

/**
 * <h1>Performs CRUD operation on {@link Car}</h1>
 * <p>
 * Provides methods to create Car, update Car, Delete Car
 * </p>
 * <p>
 * To perform CRUD operations it uses the Session and Session Factory given by
 * {@link Helper} class.
 * </p>
 * @author gopikrishnag
 * @since 2017-02-02
 */
public class PersistanceService {
	/**
	 * <h1>creates Car record</h1>
	 * <p>
	 * Parameters passed in run time, Method should insert record in table.
	 * </p>
	 * @param id
	 * @param name
	 * @param cost
	 * @param mileage
	 */
	public static void createCar(int id, String name, double cost, int mileage) {
		Session session = Helper.getSession();
		Transaction tx = session.beginTransaction();
		Car c1 = new Car();
		c1.setId(id);
		c1.setName(name);
		c1.setCost(cost);
		c1.setMileage(mileage);
		session.save(c1);
		tx.commit();
		session.close();
	}

	/**
	 * <h1>updating of car record</h1>
	 * <p>
	 * updates all properties of given Car object
	 * </p>
	 * @param id
	 * @param name
	 * @param cost
	 * @param mileage
	 */
	public static void updateCar(int id, String name, double cost, int mileage) {

		Session session = Helper.getSession();
		Transaction tx = session.beginTransaction();
		Car c = (Car) session.get(Car.class, id);
		c.setMileage(mileage);
		c.setName(name);
		c.setCost(cost);
		session.flush();
		tx.commit();
		session.close();
	}

	/**
	 * <h1>updates Car cost</h1>
	 * <p>
	 * Updates cost of Car object for given id
	 * </p>
	 * @param id
	 * @param cost
	 */

	public static void updateCarCost(int id, double cost) {

		Session session = Helper.getSession();
		Transaction tx = session.beginTransaction();
		Car c = (Car) session.get(Car.class, id);
		c.setCost(cost);
		session.flush();
		tx.commit();
		session.close();
	}

	/**
	 * <h1>Deletes Car</h1>
	 * <p>
	 * delete Car object for given id
	 * </p>
	 * @param id
	 */

	public static void deleteCar(int id) {
		Session session = Helper.getSession();
		Transaction tx = session.beginTransaction();
		Car c = (Car) session.get(Car.class, id);
		session.delete(c);
		tx.commit();
		session.close();
	}
}
