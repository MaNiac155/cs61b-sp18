public class NBody {
    public static double readRadius(String filename) {
        /**
         * read the radius of the whole universe
         */
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        /**
         * read all planets from the file, and store in a list
         */
        In in = new In(filename);
        int n = in.readInt();
        int i;
        Planet[] planetArray = new Planet[n];// 这里只是创建了列表，里面的Planet对象是空的
        for (i = 0; i < n; i++) { // 不加这个初始化的循环的话，会出现空指针
            planetArray[i] = new Planet(0.0, 0.0, 0.0, 0.0, 0.0, "a");
        }
        in.readDouble();
        for (i = 0; i < n; i++) {
            planetArray[i].xxPos = in.readDouble();
            planetArray[i].yyPos = in.readDouble();
            planetArray[i].xxVel = in.readDouble();
            planetArray[i].yyVel = in.readDouble();
            planetArray[i].mass = in.readDouble();
            planetArray[i].imgFileName = in.readString();
        }
        return planetArray;
    }

    public static void main(String[] argc) {
        double T, dt, radius;
        int i;
        String filename;
        Planet[] planets;

        T = Double.parseDouble(argc[0]);
        dt = Double.parseDouble(argc[1]);
        filename = argc[2];
        radius = readRadius(filename);
        planets = readPlanets(filename);

        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);//set scale
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (i = 0; i < planets.length; i++) {
            planets[i].draw();
        }

        StdDraw.enableDoubleBuffering();
        double t = 0;
        while (t<=T) { 
            /**
             * make it move
             */
                double[] xForces = new double[planets.length];
                double[] yForces = new double[planets.length];
                for (i = 0; i < planets.length; i++) {
                    xForces[i] = planets[i].calcNetForceExertedByX(planets);
                    yForces[i] = planets[i].calcNetForceExertedByY(planets);
                }
                StdDraw.picture(0, 0, "images/starfield.jpg");
                for (i = 0; i < planets.length; i++) {
                    planets[i].update(dt, xForces[i], yForces[i]);
                    planets[i].draw();
                }
                StdDraw.show(); 
                StdDraw.pause(10);
                t += dt;// control time
            }
            StdOut.printf("%d\n", planets.length);
            StdOut.printf("%.2e\n", radius);
            for (i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
            }
        }
    }

