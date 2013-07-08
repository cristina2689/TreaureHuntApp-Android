package com.example.treasurehunt;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;

import com.google.android.gms.maps.model.LatLng;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class CameraActivity extends Activity implements SensorEventListener {
	
	private SensorManager sensorManager;	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), sensorManager.SENSOR_DELAY_UI);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		GLSurfaceView glView = new GLSurfaceView(this);
		
		glView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		
		glView.setRenderer(new GLClearRenderer(this));
		setContentView(glView);

	
		CameraView cameraView = new CameraView(this);
		addContentView(cameraView, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
	}
	
	  @Override
	    protected void onPause() {
	        // Unregister the listener
	        sensorManager.unregisterListener(this);
	        super.onPause();
	    }

	    @Override
	    protected void onStop() {
	        // Unregister the listener
	        sensorManager.unregisterListener(this);
	        super.onStop();
	    }

	    @SuppressWarnings("deprecation")
		@Override
	    protected void onResume() {
	        super.onResume();

	        sensorManager.registerListener(this,
	                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
	                SensorManager.SENSOR_DELAY_UI);
	    }
	
	@Override
	public void onBackPressed() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("You are veeery close. Are you sure you want to give up now?")
				.setCancelable(false)
				.setPositiveButton("I give up...",new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog, final int id) {
						exit();
					}
				})
				.setNegativeButton("No!", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog, final int id) {
						dialog.cancel();
					}
				});
		
		final AlertDialog alert = builder.create();
		alert.show();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onSensorChanged(SensorEvent event) {
	
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			Utils.orientationAngle = event.values[0];
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		
	}
	
	public void exit(){
		SearchActivity.back = true;
		sensorManager.unregisterListener(this);
		super.onBackPressed();
	}
	
}

/*------------------------------------------------------------------------------------------------------------*/
class CameraView extends SurfaceView implements SurfaceHolder.Callback {
	private Camera camera;
	private SurfaceHolder mHolder;

	@SuppressWarnings("deprecation")
	public CameraView(Context context) {
		super(context);
		mHolder = getHolder();
		mHolder.setFixedSize(1, 1);
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		
		camera = Camera.open();
		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException exception) {
			camera.release();
			camera = null;
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
		Camera.Parameters p = camera.getParameters();
		p.setPreviewSize(width, height);
		camera.setParameters(p);

		
		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		camera.startPreview();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
	
		camera.stopPreview();
		camera.release();
		camera = null;
	}
}

/*------------------------------------------------------------------------------------------------------------*/

class Cube {

	private Context context;
	private boolean threadStarted;
	/** The buffer holding the vertices */
	private FloatBuffer vertexBuffer;
	/** The buffer holding the texture coordinates */
	private FloatBuffer textureBuffer;
	/** The buffer holding the indices */
	private ByteBuffer indexBuffer;

	/** Our texture pointer */
	private int[] textures = new int[1];

	/**
	 * The initial vertex definition
	 * 
	 * Note that each face is defined, even if indices are available, because of
	 * the texturing we want to achieve
	 */
	private float vertices[] = {
			// Vertices according to faces
			-2.0f,
			-2.0f,
			0.0f, // Vertex 0 -2 -2 2
			2.0f,
			-2.0f,
			0.0f, // v1 2 -2 2
			-2.0f,
			2.0f,
			0.0f, // v2 -2 2 2
			2.0f,
			2.0f,
			0.0f, // v3 2 2 2

			2.0f,
			-2.0f,
			0.0f, // v1...
			2.0f,
			-2.0f,
			0.0f, // v4
			2.0f,
			2.0f,
			0.0f, // v3
			2.0f,
			2.0f,
			0.0f, // v5

			2.0f, -2.0f, 0.0f, -2.0f, -2.0f, 0.0f, 2.0f, 2.0f, 0.0f, -2.0f,
			2.0f, 0.0f,

			-2.0f, -2.0f, 0.0f, -2.0f, -2.0f, 0.0f, -2.0f, 2.0f, 0.0f, -2.0f,
			2.0f, 0.0f,

			-2.0f, -2.0f, 0.0f, 2.0f, -2.0f, 0.0f, -2.0f, -2.0f, 0.0f, 2.0f,
			-2.0f, 0.0f,

			-2.0f, 2.0f, 0.0f, 2.0f, 2.0f, 0.0f, -2.0f, 2.0f, 0.0f, 2.0f, 2.0f,
			0.0f, };

	/** The initial texture coordinates (u, v) */
	private float texture[] = {
			// Mapping coordinates for the vertices
			0.0f, 0.0f, // 0 0
			0.0f, 1.0f, // 0 1
			1.0f, 0.0f, // 1 0
			1.0f, 1.0f, // 1 1

			0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,

			0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,

			0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,

			0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,

			0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,

	};

	/** The initial indices definition */
	private byte indices[] = {
			// Faces definition
			0, 1, 3, 0, 3,
			2, // Face front
			4, 5, 7, 4, 7,
			6, // Face right
			8, 9, 11, 8, 11,
			10, // ...
			12, 13, 15, 12, 15, 14, 16, 17, 19, 16, 19, 18, 20, 21, 23, 20, 23,
			22, };

	/**
	 * The Cube constructor.
	 * 
	 * Initiate the buffers.
	 */
	public Cube(Context context) {
		
		this.context = context;
		threadStarted = false;
		
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		//
		byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);

		//
		indexBuffer = ByteBuffer.allocateDirect(indices.length);
		indexBuffer.put(indices);
		indexBuffer.position(0);
	}

	/**
	 * The object own drawing function. Called from the renderer to redraw this
	 * instance with possible changes in values.
	 * 
	 * @param gl
	 *            - The GL Context
	 */
	public void draw(GL10 gl) {
		boolean isSeen;
		isSeen = Utils.canBeSeen(new LatLng(Utils.userLocation.getLatitude(),
				Utils.userLocation.getLongitude()), SearchActivity.hint);
		// Bind our only previously generated texture in this case
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		// Point to our buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		// Set the face rotation
		gl.glFrontFace(GL10.GL_CCW);

		// Enable the vertex and texture state
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);

		// Draw the vertices as triangles, based on the Index Buffer information
		if (isSeen) {
			gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
					GL10.GL_UNSIGNED_BYTE, indexBuffer);
			if (!threadStarted){
				threadStarted = true;
				Utils.thread = new FinishThread(context);
				Utils.thread.start();
			}
		}

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}

	/**
	 * Load the textures
	 * 
	 * @param gl
	 *            - The GL Context
	 * @param context
	 *            - The Activity context
	 */
	public void loadGLTexture(GL10 gl, Context context) {

		Log.d("debug", "se apeleaza");

		// Get the texture from the Android resource directory
		InputStream is = context.getResources()
				.openRawResource(R.drawable.icon);
		Bitmap bitmap = null;
		try {
			// BitmapFactory is an Android graphics utility for images
			bitmap = BitmapFactory.decodeStream(is);

		} finally {
			// Always clear and close
			try {
				is.close();
				is = null;
			} catch (IOException e) {
			}
		}

		// Generate one texture pointer...
		gl.glGenTextures(1, textures, 0);
		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		// Create Nearest Filtered Texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);

		// Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				GL10.GL_REPEAT);

		// Use the Android GLUtils to specify a two-dimensional texture image
		// from our bitmap
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		// Clean up
		bitmap.recycle();
	}
}

/*------------------------------------------------------------------------------------------------------------*/
class GLClearRenderer implements Renderer {

	
	private Context context;
	private Cube mCube;
	/** Constructor to set the handed over context */
	public GLClearRenderer(Context context) {
		this.context = context;
		mCube = new Cube(context);
	}

	public void onDrawFrame(GL10 gl) {

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -10.0f); 
		gl.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
		mCube.draw(gl);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// This is called whenever the dimensions of the surface have changed.
		// We need to adapt this change for the GL viewport.
		// mCube.loadGLTexture(gl, this.context);
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
				100.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		mCube.loadGLTexture(gl, this.context);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glDepthFunc(GL10.GL_LEQUAL);

		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}
}
