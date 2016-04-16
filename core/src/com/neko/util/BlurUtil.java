package com.neko.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;


/**
 * �߼���Ⱦ - ��˹ģ��
 * @author WHS
 *  Date 2015/02/09 16:07
 */
public class BlurUtil  {

    public  final static float MAX_BLUR = 5.0F;     // ����ģ��ϵ��
    private static SpriteBatch blurBatch;        
    private static String vertexShader;            // ����
    private static String fragmentShader;            // Ƭ��
    private static ShaderProgram shader;            // �Զ�����ɫ��
    private static FrameBuffer frameBufferA;        // ������A��ʵ���Ͼ������������һ��������Ŀ���
    private static FrameBuffer frameBufferB;        // ������B
    private static float radius = 0.0F;                // ��ʼ��ģ��ϵ��
    private static int fbo_size = 1024;    // �������С
    private static float  blur = 0.0F;        // ģ��ϵ��
    private static  float time = 0;            // ƫ�Ƶ�ʱ��
    private static float xOffset = 0.8F;    // x��ƫ�ƣ�ˮƽ��Ⱦ
    private static float yOffset = 0.8F;    // y��ƫ�ƣ���ֱ��Ⱦ
    
    static {
        //����
        vertexShader = "uniform mat4  u_projTrans;\n " 
                + "attribute vec2 a_position;\n "
                + "attribute vec2 a_texCoord0;\n"
                + "attribute vec4 a_color;\n"
                + "varying vec4 vColor;\n"
                + "varying vec2 vTexCoord;\n"
                + "void main() {\n"
                + "    vColor = a_color;\n"
                + "    vTexCoord = a_texCoord0;\n"
                + "    gl_Position = u_projTrans * vec4(a_position, 0.0, 1.0);\n"
                + "}";
        //Ƭ��
        fragmentShader = "varying vec4 vColor;\n"
                + "varying vec2 vTexCoord;\n"
                +"uniform sampler2D u_texture;\n"
                + "uniform float resolution;\n"
                + "uniform float radius;\n"
                + "uniform vec2 dir;\n"
                + "void main() {\n"
                + "    vec4 sum = vec4(0.0);\n"
                + "    vec2 tc = vTexCoord;\n"
                + "    float blur = radius/resolution; \n"
                + "    float hstep = dir.x;\n"
                + "    float vstep = dir.y;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x - 4.0*blur*hstep, tc.y - 4.0*blur*vstep)) * 0.0162162162;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x - 3.0*blur*hstep, tc.y - 3.0*blur*vstep)) * 0.0540540541;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x - 2.0*blur*hstep, tc.y - 2.0*blur*vstep)) * 0.1216216216;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x - 1.0*blur*hstep, tc.y - 1.0*blur*vstep)) * 0.1945945946;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x, tc.y)) * 0.2270270270;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x + 1.0*blur*hstep, tc.y + 1.0*blur*vstep)) * 0.1945945946;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x + 2.0*blur*hstep, tc.y + 2.0*blur*vstep)) * 0.1216216216;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x + 3.0*blur*hstep, tc.y + 3.0*blur*vstep)) * 0.0540540541;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x + 4.0*blur*hstep, tc.y + 4.0*blur*vstep)) * 0.0162162162;\n"
                + "    gl_FragColor = vColor * vec4(sum.rgb, 1.0);\n" + "}";

//        vertexShader = Gdx.files.internal("blur/vertex.vert").readString(); // ��ȡ������ɫ
//        fragmentShader = Gdx.files.internal("blur/fragment.frag").readString(); // ��ȡƬ����ɫ
        frameBufferA = new FrameBuffer(Pixmap.Format.RGBA8888, 800, 480, false);
        frameBufferB = new FrameBuffer(Pixmap.Format.RGBA8888, 800, 480, false);
        shader = new ShaderProgram(vertexShader, fragmentShader);    
        blurBatch = new SpriteBatch();
        //blurBatch.setProjectionMatrix(Gaussian.game.camera.combined);
     }
    
    /**
     *     ��ָ����̨����ģ������
     *  �������壬ע�ⷽ�������˳���Լ�begin()�� end()���
     *  ��Щ�ֻ���֧�ֵ���shader.isCompiled() == false �޷�������ɫ
     *  @param stage
     */
    public static void blur(Stage stage) {

        if (shader != null && shader.isCompiled()) {
            time += Gdx.graphics.getDeltaTime();
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            // ʱ����ƣ���ʾ��̬�仯
            blur = time < MAX_BLUR ? time : MAX_BLUR; 
            blurRander(stage);
            horizontalBlur();
            verticalBlur();
        }

    }
    
    /**
     *  ģ��������Ⱦ
     */
    private static void blurRander(Stage stage){
    
        //��ִ��frameBufferA����Ĭ����Ⱦ֮�µ�������
        frameBufferA.begin();
        blurBatch.begin();    
        shader.begin();
        /** ��Ϊ��ѭ��ִ�У�����Բ����������ã���������shader������Ҫģ����stage����**/
        shader.setUniformf("dir", 0f, 0f);      
        shader.setUniformf("radius", radius );  
        shader.setUniformf("resolution", fbo_size);  
        blurBatch.setShader(shader);
        //�������ú����stage
        stage.draw();                    
        stage.getRoot().draw(blurBatch, 1);
        //����ˢ��
        blurBatch.flush();
        //��õ�һ��������
        frameBufferA.end();
    }
    
    /**
     *  ��ֱģ����Ⱦ
     */
    private static void horizontalBlur()   {

        /**����Ϊ��ֱģ����shader**/
        blurBatch.setShader(shader);
        shader.setUniformf("dir", xOffset, 0f);
        shader.setUniformf("radius",blur );
        
        frameBufferB.begin();
        
        blurBatch.draw(frameBufferA.getColorBufferTexture(), 0, 0);//����������A
        blurBatch.flush();
        frameBufferB.end();
    }
    
    /**
     *  ˮƽģ����Ⱦ
     */
    private static void verticalBlur()   {
        /*
         * ����Ϊ ˮƽģ��shader�ٽ��л���
         */
        shader.setUniformf("dir", 0f, yOffset);
        shader.setUniformf("radius",blur );
        
        blurBatch.draw(frameBufferB.getColorBufferTexture(), 0, 0);// ����������B
        blurBatch.flush();
        blurBatch.end();
        shader.end();
        
    }
    
    public static void dispose() {
        blurBatch.dispose();
        shader.dispose();    
    }

}