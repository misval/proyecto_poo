package jgame.gradle;


public class Camara {

	private double x,y;

	private double resX,resY;

    public Camara(double x,double y) {
    	this.x=x;
    	this.y=y;
    }

	public void seguirPersonaje(Heroe obj){
		Mundo m=Mundo.getInstance();
		//this.x = -b.getX()+(m.getWidth()/8);
		this.x = -obj.getX()+resX/2;
		if (this.x>0){
				this.x=0;
		}

		if(this.x < -(m.getWidth()-resX)){
			this.x = -(m.getWidth()-resX);
		}


	}
	public void setViewPort(double x,double y){
		setRegionVisible(x,y);
	}
	public void setRegionVisible(double x,double y){
		resX=x;
		resY=y;
	}
    public void setX(double x){
    	this.x=x;

    }
     public void setY(double y){
    	this.y=y;

    }
    public double getX(){
    	return this.x;

    }
     public double getY(){
    	return this.y;

    }

    /////
    /*
     * init
     * g2d.translate(camera.getX(),camera.getY());
     *
     *
     * end
     * g2d.translate(-camera.getX(),-camera.getY());
     **/

}