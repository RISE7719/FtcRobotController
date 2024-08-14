package odometry;

import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class OdoTest {

    public static final double TICKS_PER_REV = 1;
    public static final double MAX_RPM = 1;

    public static final boolean RUN_USING_ENCODER = true;
    public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(0,0,0,)
        getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));

    public static double WHEEL_RADIUS = 2;
    public static double GEAR_RATIO = 2.0 / 3.0;
    public static double TRACK_WIDTH = 1;
    public static double kV = 1.0 / rpmToVelocity(MAX_RPM);
    public static double kA = 0;
    public static double kStatic = 0;
    public static double MAX_VEL = 36;
    public static double MAX_ACCEL = 30;
    public static double MAX_ANG_VEL = Math.toRadians(180);
    public static double MAX_ANG_ACCEL = Math.toRadians(180);

}