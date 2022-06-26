public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    

    public Planet(double xP,double yP,double xV,
                double yV,double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
        
    }
    public Planet(Planet b){
        // this.xxPos=b.xxPos;
        // this.yyPos=b.yyPos;
        // this.xxVel=b.xxVel;
        // this.yyVel=b.yyVel;
        // this.mass=b.mass;
        // this.imgFileName=b.imgFileName;
        this(b.xxPos,b.yyPos,b.xxVel,b.yyVel,b.mass,b.imgFileName);
    }

    public double calcDistance(Planet that){
        double r,dx,dy;
        dx=that.xxPos-this.xxPos;
        dy=that.yyPos-this.yyPos;
        r=Math.sqrt(dx*dx+dy*dy);
        return r;
    }
    public double calcForceExertedBy(Planet that){
        double force;
        double G=6.67e-11;
        double r=calcDistance(that);

        force=G*this.mass*that.mass/(r*r);
        return force;
    }
    public double calcForceExertedByX(Planet that){
        double force=calcForceExertedBy(that);
        double r=calcDistance(that);
        double dx=that.xxPos-this.xxPos;
        double f_x=force*dx/r;

        return f_x;
    }
    public double calcForceExertedByY(Planet that){
        double force=calcForceExertedBy(that);
        double r=calcDistance(that);
        double dy=that.yyPos-this.yyPos;
        double f_y=force*dy/r;

        return f_y;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double f_x=0;
        int i;
        for(i=0;i<allPlanets.length;i++){
            if(!this.equals(allPlanets[i])){
                f_x+=calcForceExertedByX(allPlanets[i]);
            }
        }
        return f_x;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double f_y=0;
        int i;
        for(i=0;i<allPlanets.length;i++){
            if(!this.equals(allPlanets[i])){
                f_y+=calcForceExertedByY(allPlanets[i]);
            }
        }
        return f_y;
    }
    public void update(double dt,double fX,double fY){
        double aX,aY;
        aX=fX/this.mass;
        aY=fY/this.mass;
        this.xxVel+=aX*dt;
        this.yyVel+=aY*dt;
        this.xxPos+=this.xxVel*dt;
        this.yyPos+=this.yyVel*dt;
        
    }
    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }

}