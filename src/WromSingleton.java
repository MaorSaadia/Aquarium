/**
 * Singleton class that create object of worm and enable to create Singelton class create object of worm only once.
 */
    public class WromSingleton {
        static private WromSingleton instance = null;

        /* A private Constructor prevents any other
         * class from instantiating.*/
        private WromSingleton() {
        }

        /* Static 'instance' method */
        public static WromSingleton getInstance() {
            if (instance == null)
                instance = new WromSingleton();
            return instance;
        }

        public static void set() {
           if(AquaPanel.getWorm_instance()!=null)
               AquaPanel.setWorm_instance(null);
        }

    }

