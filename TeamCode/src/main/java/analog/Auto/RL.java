package analog.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Disabled
@Autonomous
public class RL extends LinearOpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor Conveyor;
    DcMotor Lift;
    Servo Outtake;
    Servo Wrist;
    TouchSensor Touch;

    @Override
    public void runOpMode() throws InterruptedException {

        double speed = 0.2;

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        Conveyor = hardwareMap.get(DcMotor.class, "Conveyor");
        Lift = hardwareMap.get(DcMotor.class, "Lift");
        Outtake = hardwareMap.get(Servo.class, "Outtake");
        Wrist = hardwareMap.get(Servo.class, "Wrist");
        Touch = hardwareMap.get(TouchSensor.class, "Touch");


        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //Resetting Encoder Values to Zero
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //Make the motors run using encoders
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Make motors brake when not running
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();


        Wrist.setPosition(0.078);

        //Strafe Left
        frontLeft.setTargetPosition(-410);
        backRight.setTargetPosition(-410);
        frontRight.setTargetPosition(410);
        backLeft.setTargetPosition(410);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (frontRight.isBusy()) {
            frontLeft.setPower(0.1);
            frontRight.setPower(0.1);
            backLeft.setPower(0.1);
            backRight.setPower(0.1);
        }

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(1000);

        //Backward
        frontLeft.setTargetPosition(-150);
        backRight.setTargetPosition(-150);
        frontRight.setTargetPosition(-150);
        backLeft.setTargetPosition(-150);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while (frontRight.isBusy()) {
            frontLeft.setPower(0.1);
            frontRight.setPower(0.1);
            backLeft.setPower(0.1);
            backRight.setPower(0.1);
        }

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(500);

        //Strafe Left
        frontLeft.setTargetPosition(-200);
        backRight.setTargetPosition(-200);
        frontRight.setTargetPosition(200);
        backLeft.setTargetPosition(200);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (frontRight.isBusy()) {
            frontLeft.setPower(0.1);
            frontRight.setPower(0.1);
            backLeft.setPower(0.1);
            backRight.setPower(0.1);


            if (Touch.isPressed()) {
                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                //Strafe Right
                frontLeft.setTargetPosition(130);
                backRight.setTargetPosition(130);
                frontRight.setTargetPosition(-130);
                backLeft.setTargetPosition(-130);

                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                while (frontRight.isBusy()) {
                    frontLeft.setPower(speed);
                    frontRight.setPower(speed);
                    backLeft.setPower(speed);
                    backRight.setPower(speed);
                }

                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                sleep(400);

                //Turn 90 left
                frontLeft.setTargetPosition(-440);
                backRight.setTargetPosition(440);
                frontRight.setTargetPosition(440);
                backLeft.setTargetPosition(-440);

                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                while (frontRight.isBusy()) {
                    frontLeft.setPower(0.1);
                    frontRight.setPower(0.1);
                    backLeft.setPower(0.1);
                    backRight.setPower(0.1);
                }

                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                //Strafe right
                frontLeft.setTargetPosition(30);
                backRight.setTargetPosition(30);
                frontRight.setTargetPosition(-30);
                backLeft.setTargetPosition(-30);

                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                while (frontRight.isBusy()) {
                    frontLeft.setPower(0.1);
                    frontRight.setPower(0.1);
                    backLeft.setPower(0.1);
                    backRight.setPower(0.1);
                }

                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                sleep(1000);
                Conveyor.setPower(0.6);
                sleep(1500);
                Conveyor.setPower(0);

                //Strafe right
                frontLeft.setTargetPosition(-100);
                backRight.setTargetPosition(-100);
                frontRight.setTargetPosition(-100);
                backLeft.setTargetPosition(-100);

                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                while (frontRight.isBusy()) {
                    frontLeft.setPower(0.1);
                    frontRight.setPower(0.1);
                    backLeft.setPower(0.1);
                    backRight.setPower(0.1);
                }

                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
        }

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(500);
        //Turn Right 90
        frontLeft.setTargetPosition(440);
        backRight.setTargetPosition(-440);
        frontRight.setTargetPosition(-440);
        backLeft.setTargetPosition(440);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (frontRight.isBusy()) {
            frontLeft.setPower(0.1);
            frontRight.setPower(0.1);
            backLeft.setPower(0.1);
            backRight.setPower(0.1);
        }

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(500);

        //Backward
        frontLeft.setTargetPosition(-180);
        backRight.setTargetPosition(-180);
        frontRight.setTargetPosition(-180);
        backLeft.setTargetPosition(-180);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (frontRight.isBusy()) {
            frontLeft.setPower(0.1);
            frontRight.setPower(0.1);
            backLeft.setPower(0.1);
            backRight.setPower(0.1);
        }

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(500);

        //Strafe Left
        frontLeft.setTargetPosition(-200);
        backRight.setTargetPosition(-200);
        frontRight.setTargetPosition(200);
        backLeft.setTargetPosition(200);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (frontRight.isBusy()) {
            frontLeft.setPower(0.1);
            frontRight.setPower(0.1);
            backLeft.setPower(0.1);
            backRight.setPower(0.1);

            if (Touch.isPressed()) {
                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                //Strafe Right
                frontLeft.setTargetPosition(130);
                backRight.setTargetPosition(130);
                frontRight.setTargetPosition(-130);
                backLeft.setTargetPosition(-130);

                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                while (frontRight.isBusy()) {
                    frontLeft.setPower(speed);
                    frontRight.setPower(speed);
                    backLeft.setPower(speed);
                    backRight.setPower(speed);
                }

                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                sleep(400);

                //Turn 90 left
                frontLeft.setTargetPosition(-440);
                backRight.setTargetPosition(440);
                frontRight.setTargetPosition(440);
                backLeft.setTargetPosition(-440);

                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                while (frontRight.isBusy()) {
                    frontLeft.setPower(0.1);
                    frontRight.setPower(0.1);
                    backLeft.setPower(0.1);
                    backRight.setPower(0.1);
                }

                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                //Strafe right
                frontLeft.setTargetPosition(30);
                backRight.setTargetPosition(30);
                frontRight.setTargetPosition(-30);
                backLeft.setTargetPosition(-30);

                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                while (frontRight.isBusy()) {
                    frontLeft.setPower(0.1);
                    frontRight.setPower(0.1);
                    backLeft.setPower(0.1);
                    backRight.setPower(0.1);
                }

                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                sleep(1000);
                Conveyor.setPower(0.6);
                sleep(1500);
                Conveyor.setPower(0);

                //Strafe right
                frontLeft.setTargetPosition(-100);
                backRight.setTargetPosition(-100);
                frontRight.setTargetPosition(-100);
                backLeft.setTargetPosition(-100);

                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                while (frontRight.isBusy()) {
                    frontLeft.setPower(0.1);
                    frontRight.setPower(0.1);
                    backLeft.setPower(0.1);
                    backRight.setPower(0.1);
                }

                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
        }

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(500);

        //Forward
        frontLeft.setTargetPosition(400);
        backRight.setTargetPosition(400);
        frontRight.setTargetPosition(400);
        backLeft.setTargetPosition(400);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (frontRight.isBusy()) {
            frontLeft.setPower(0.1);
            frontRight.setPower(0.1);
            backLeft.setPower(0.1);
            backRight.setPower(0.1);
        }

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(500);

        //Turn 270
        frontLeft.setTargetPosition(-660);
        backRight.setTargetPosition(660);
        frontRight.setTargetPosition(660);
        backLeft.setTargetPosition(-660);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (frontRight.isBusy()) {
            frontLeft.setPower(0.1);
            frontRight.setPower(0.1);
            backLeft.setPower(0.1);
            backRight.setPower(0.1);
        }

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(500);

        frontRight.setTargetPosition(120);
        backLeft.setTargetPosition(120);

        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (frontRight.isBusy()) {
            frontRight.setPower(0.1);
            backLeft.setPower(0.1);
        }
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(1000);
        Conveyor.setPower(0.7);
        sleep(1500);
        Conveyor.setPower(0);

        //Strafe right
        frontLeft.setTargetPosition(-100);
        backRight.setTargetPosition(-100);
        frontRight.setTargetPosition(-100);
        backLeft.setTargetPosition(-100);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (frontRight.isBusy()) {
            frontLeft.setPower(0.1);
            frontRight.setPower(0.1);
            backLeft.setPower(0.1);
            backRight.setPower(0.1);
        }
    }
}


