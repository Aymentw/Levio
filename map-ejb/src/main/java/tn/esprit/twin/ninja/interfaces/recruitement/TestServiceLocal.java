package tn.esprit.twin.ninja.interfaces.recruitement;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.twin.ninja.persistence.recruitment.Test;



@Local
public interface TestServiceLocal {
public int passeTest(Test t);
public boolean getResultTest(int idTest);
public int addTest(Test t);
public Test getTest(int idTest);


}
