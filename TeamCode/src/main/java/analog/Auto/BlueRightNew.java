package analog.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Autonomous
public class BlueRightNew extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor Conveyor;
    DcMotor Lift;
    Servo Outtake;
    Servo Wrist;
    Servo PW;
    static final double     COUNTS_PER_MOTOR_REV    =  70;
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 5.51181 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.5;
    static final double     TURN_SPEED              = 0.2;
    static final double     STRAFE_SPEED            = 0.15;

    static final double     DRIVE_SLOW_SPEED        = 0.15;

    boolean USE_WEBCAM;

    private ElapsedTime runtime = new ElapsedTime();
    TfodProcessor myTfodProcessor;
    VisionPortal myVisionPortal;

    /**
     * This function is executed when this OpMode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor .class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        Conveyor = hardwareMap.get(DcMotor.class, "Conveyor");
        Lift = hardwareMap.get(DcMotor.class, "Lift");
        Outtake = hardwareMap.get(Servo .class, "Outtake");
        Wrist = hardwareMap.get(Servo.class, "Wrist");
        PW = hardwareMap.get(Servo.class, "PullUpServo");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Make the motors run using encoders
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // This 2023-2024 OpMode illustrates the basics of TensorFlow Object Detection, using
        // a custom TFLite object detection model.
        USE_WEBCAM = true;
        // Initialize TFOD before waitForStart.
        initTfod();
        // Wait for the match to begin.
        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch Play to start OpMode");
        telemetry.update();

        waitForStart();

        resetRuntime();

        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                int moveNum = telemetryTfod();
                telemetry.update();
                // Push telemetry to the Driver Station.
                if (moveNum == 1) {
                    telemetry.addData("It ran: ", "1");
                    telemetry.update();
                    movementSet1();

                    sleep(30000);
                } else if (moveNum == 2) {
                    telemetry.addData("It ran: ", "2");
                    telemetry.update();
                    movementSet2();
                    sleep(30000);
                } else if (moveNum == 3) {
                    telemetry.addData("It ran: ", "3");
                    telemetry.update();
                    movementSet3();
                    sleep(30000);
                }

                /*
                telemetry.update();
                if (gamepad1.dpad_down) {
                    // Temporarily stop the streaming session.
                    myVisionPortal.stopStreaming();
                } else if (gamepad1.dpad_up) {
                    // Resume the streaming session if previously stopped.
                    myVisionPortal.resumeStreaming();
                } // */
                // Share the CPU.
                sleep(20);
            }
        }
    }


    private void movementSet1() {
        Wrist.setPosition(0.130);
        Outtake.setPosition(0.32);
        PW.setPosition(0);


        encoderDrive(DRIVE_SLOW_SPEED,  12,  12, 12, 12, 1.0);
        encoderDrive(STRAFE_SPEED,  -115,  115, 115, -115, 2.0);
        encoderDrive(TURN_SPEED,   250, -250, 250, -250, 3.0);
        encoderDrive(DRIVE_SLOW_SPEED,  6,  6, 6, 6, 0.5);

        Conveyor.setPower(1);
        sleep(600);
        Conveyor.setPower(0);

        encoderDrive(DRIVE_SLOW_SPEED,  -15,  -15, -15, -15, 0.5);
        encoderDrive(TURN_SPEED,   -250, 250, -250, 250, 3.0);
        encoderDrive(STRAFE_SPEED,  80,  -80, -80, 80,2.0);
        encoderDrive(DRIVE_SLOW_SPEED,  -440,  -440, -440, -440, 8.0);

    }

    private void movementSet2() {
        Wrist.setPosition(0.095);
        Outtake.setPosition(0.32);
        PW.setPosition(0);
        sleep(500);

        encoderDrive(DRIVE_SLOW_SPEED,  10,  10, 10, 10, 3.5);
        encoderDrive(STRAFE_SPEED,  -135,  135, 135, -135, 3.0);
        encoderDrive(TURN_SPEED,   -110, 110, -110, 110, 4.0);

        Conveyor.setPower(1);
        sleep(600);
        Conveyor.setPower(0);

        encoderDrive(DRIVE_SLOW_SPEED,  -117,  -117, -117, -117, 3.5);
        encoderDrive(TURN_SPEED,   105, -105, 105, -105, 4.0);
        encoderDrive(DRIVE_SLOW_SPEED,  -440,  -440, -440, -440, 8.0);

    }

    private void movementSet3() {
        Wrist.setPosition(0.130);
        Outtake.setPosition(0.32);
        PW.setPosition(0);
        sleep(500);

        encoderDrive(DRIVE_SLOW_SPEED,  15,  15, 15, 15, 4.0);
        encoderDrive(STRAFE_SPEED,  -150,  150, 150, -150, 3.0);

        Conveyor.setPower(1);
        sleep(600);
        Conveyor.setPower(0);

        encoderDrive(STRAFE_SPEED,  140,  -140, -140, 140, 4.0);
        encoderDrive(DRIVE_SLOW_SPEED,  -440,  -440, -440, -440, 8.0);
    }


    /**
     * Initialize TensorFlow Object Detection.
     */
    private void initTfod() {
        TfodProcessor.Builder myTfodProcessorBuilder;
        VisionPortal.Builder myVisionPortalBuilder;

        // First, create a TfodProcessor.Builder.
        myTfodProcessorBuilder = new TfodProcessor.Builder();
        // Set the name of the file where the model can be found.
        myTfodProcessorBuilder.setModelFileName("blue among us");
        // Set the full ordered list of labels the model is trained to recognize.
        myTfodProcessorBuilder.setModelLabels(JavaUtil.createListWith("model_20240117_142750.tflite", null));
        // Set the aspect ratio for the images used when the model was created.
        myTfodProcessorBuilder.setModelAspectRatio(16 / 8);
        // Create a TfodProcessor by calling build.
        myTfodProcessor = myTfodProcessorBuilder.build();
        // Next, create a VisionPortal.Builder and set attributes related to the camera.
        myVisionPortalBuilder = new VisionPortal.Builder();
        if (USE_WEBCAM) {
            // Use a webcam.
            myVisionPortalBuilder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            // Use the device's back camera.
            myVisionPortalBuilder.setCamera(BuiltinCameraDirection.BACK);
        }
        // Add myTfodProcessor to the VisionPortal.Builder.
        myVisionPortalBuilder.addProcessor(myTfodProcessor);
        // Create a VisionPortal by calling build.
        myVisionPortal = myVisionPortalBuilder.build();
    }

    /**
     * Display info (using telemetry) for a detected object
     */
    private int telemetryTfod() {
        List<Recognition> myTfodRecognitions;
        Recognition myTfodRecognition;
        float x;
        float y;

        // Get a list of recognitions from TFOD.
        myTfodRecognitions = myTfodProcessor.getRecognitions();
        telemetry.addData("# Objects Detected", JavaUtil.listLength(myTfodRecognitions));
        // Iterate through list and call a function to display info for each recognized object.
        for (Recognition myTfodRecognition_item : myTfodRecognitions) {
            myTfodRecognition = myTfodRecognition_item;
            // Display info about the recognition.
            telemetry.addLine("");
            // Display label and confidence.
            // Display the label and confidence for the recognition.
            telemetry.addData("Image", myTfodRecognition.getLabel() + " (" + JavaUtil.formatNumber(myTfodRecognition.getConfidence() * 100, 0) + " % Conf.)");
            // Display position.
            x = (myTfodRecognition.getLeft() + myTfodRecognition.getRight()) / 2;
            y = (myTfodRecognition.getTop() + myTfodRecognition.getBottom()) / 2;
            // Display the position of the center of the detection boundary for the recognition

            if (y > 125) {

                if (x > 400) {
                    return 3;
                } else if (x < 200) {
                    return 1;
                } else {
                    return 2;
                }

            }


        }

        return -1;
    }

    public void encoderDrive(double speed,
                             double FlInches, double FrInches, double BlInches, double BrInches,
                             double timeoutS) {
        int newFlTarget;
        int newFrTarget;
        int newBlTarget;
        int newBrTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFlTarget = frontLeft.getCurrentPosition() + (int) (FlInches * COUNTS_PER_INCH);
            newFrTarget = frontRight.getCurrentPosition() + (int) (FrInches * COUNTS_PER_INCH);
            newBrTarget = backRight.getCurrentPosition() + (int) (BrInches * COUNTS_PER_INCH);
            newBlTarget = backLeft.getCurrentPosition() + (int) (BlInches * COUNTS_PER_INCH);
            frontLeft.setTargetPosition(newFlTarget);
            frontRight.setTargetPosition(newFrTarget);
            backLeft.setTargetPosition(newBlTarget);
            backRight.setTargetPosition(newBrTarget);

            // Turn On RUN_TO_POSITION
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            frontRight.setPower(Math.abs(speed));
            frontLeft.setPower(Math.abs(speed));
            backLeft.setPower(Math.abs(speed));
            backRight.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    ( (frontLeft.isBusy() || frontRight.isBusy()) && (backLeft.isBusy() || backRight.isBusy()))) {
            }

            // Stop all motion;
            frontRight.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);

            // Turn off RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(1000);   // optional pause after each move.

        }
    }
}


