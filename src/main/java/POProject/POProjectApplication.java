package POProject;

import POProject.core.utils.HibernateUtils;

public class POProjectApplication {


    public static void main(String[] args){
        HibernateUtils.OpenConnection("hibernate.cfg.xml");
    }

}
