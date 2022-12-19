package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private Sprite sprite1;
	private Texture targetTexture;
	
	public class ProjectileEquation
	{
		public float gravity;
		public Vector2 startVelocity;
		public Vector2 startPoint;

		public ProjectileEquation()
		{   }

		public float getX(float t)
		{
			if (this.startVelocity!=null) return startVelocity.x*t + startPoint.x;
			else return 0;
		}


		public float getY(float t)
		{
			if(startVelocity!=null) return 0.5f*gravity*t*t + startVelocity.y*t + startPoint.y;
			else return 0;
		}
	}



	public Sprite trajectorySprite;
	public ProjectileEquation projectileEquation;

	public void trajectorystuff(ProjectileEquation projectileEquation){

		projectileEquation.gravity = -9.8f;
		projectileEquation.startVelocity = new Vector2(50, 100);
		projectileEquation.startPoint = new Vector2(80, 250);
	}










	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("abrams.png");
		targetTexture = new Texture("ellipse.png");

		trajectorySprite = new Sprite(targetTexture);
		//trajectorySprite.setSize(1, 1);
		//trajectorySprite.setPosition(5, 5);
		sprite1= new Sprite(img);
		sprite1.setSize(100,100);
		sprite1.setPosition(80,150);
		projectileEquation=new ProjectileEquation();
		trajectorystuff(projectileEquation);

	}
	public void draw(){
		float t = 0f;
		float x = 0f;
		float y = 0f;

		for (int i = 0; i<5; i++) {
			x =  projectileEquation.getX(t);
			y =  projectileEquation.getY(t);
			System.out.println("x: " + x + " y: " + y);

			batch.setColor(1, 0, 0, 1);
			batch.draw(trajectorySprite, x, y, 20, 20);


			t += 1f;
		}




	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		sprite1.draw(batch);
		draw();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		targetTexture.dispose();
	}
}
