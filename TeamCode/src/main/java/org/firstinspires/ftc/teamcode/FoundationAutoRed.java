package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Foundation Auto Red", group="Auto")
public class FoundationAutoRed extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;
    private DcMotor frontRight;


    private Servo latchLeft;
    private Servo latchRight;
    private double latchLeftPosition = 0.0;
    private double latchRightPosition = 0.0;

    private double latchLeftFoundation = 0.0;
    private double latchRightFoundation = 0.744;
    private double latchLeftStone = 0.367;
    private double latchRightStone = 0.291;
    private double latchLeftRelease = 0.536;
    private double latchRightRelease = 0.113;

    private double firstForwardDuration = 1.7;
    private double firstStrafeDuration = .84;
    private double latchDuration = 2;
    private double firstReverseDuration = 1.67;
    private double turnDuration = 1;
    private double parkDuration = 1.8;
    private double incrementAmount = 0.0000005;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        latchLeft = hardwareMap.servo.get("latchLeft");
        latchRight = hardwareMap.servo.get("latchRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        runtime.reset();

        manageDurationParameters();

        telemetry.addData("1. firstForwardDuration", firstForwardDuration);
        telemetry.addData("2. firstStrafeDuration", firstStrafeDuration);
        telemetry.addData("3. latchDuration", latchDuration);
        telemetry.addData("4. firstReverseDuration", firstReverseDuration);
        telemetry.addData("5. turnDuration", turnDuration);
        telemetry.addData("6. latchDuration", latchDuration);
        telemetry.addData("7. parkDuration", parkDuration);
        telemetry.update();

        waitForStart();

        //-----------------------------------------------------
        //drives straight for 1 seconds
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < firstForwardDuration) {
            drive(1, 0, 0);
        }

        drive(0, 0, 0);

        //-----------------------------------------------------
        //strafe right to center robot to foundation
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < firstStrafeDuration) {
            drive(1, -Math.PI / 2, 0);
        }

        drive(0, 0, 0);

        //-------------------------------------------------------------
        // latch

        latchRight.setPosition(latchRightFoundation);
        latchLeft.setPosition(latchLeftFoundation);

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < latchDuration) {
        }

        //-------------------------------------------------------------
        // drive backwards

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < firstReverseDuration) {
            drive(1, Math.PI, 0);
        }

        drive(0, 0, 0);


        //-------------------------------------------------------------
        // turn right

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < turnDuration) {
            drive(0, 0, 1);
        }

        drive(0, 0, 0);


        //-------------------------------------------------------------
        // unlatch

        latchRight.setPosition(latchRightRelease);
        latchLeft.setPosition(latchLeftRelease);

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < latchDuration) {
        }

        //-------------------------------------------------------------
        // drive backwards

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < parkDuration) {
            drive(1, Math.PI, 0);
        }

        drive(0, 0, 0);

    }

    private void manageDurationParameters() {

        boolean optionLoop = true;
        while (optionLoop) {
            runtime.reset();
            while(opModeIsActive() && runtime.milliseconds() < 1){
                continue;
            }

            telemetry.addData("(Y increase, A decrease, B accept)", "");
            telemetry.addData("1. Drive forward time", firstForwardDuration);
            telemetry.update();

            if (gamepad1.y) {
                firstForwardDuration += incrementAmount;
            } else if (gamepad1.a) {
                firstForwardDuration -= incrementAmount;
            }

            if(gamepad1.b) {
                optionLoop = false;
            }
        }

        while(gamepad1.b){
            continue;
        }

        optionLoop = true;

        while (optionLoop) {
            runtime.reset();
            while(opModeIsActive() && runtime.milliseconds() < 1){
                continue;
            }

            telemetry.addData("(Y increase, A decrease, B accept)", "");
            telemetry.addData("2. Strafe left time", firstStrafeDuration);
            telemetry.update();

            if (gamepad1.y) {
                firstStrafeDuration += incrementAmount;
            } else if (gamepad1.a) {
                firstStrafeDuration -= incrementAmount;
            }

            if(gamepad1.b) {
                optionLoop = false;
            }
        }

        while(gamepad1.b){
            continue;
        }

        optionLoop = true;

        while (optionLoop) {
            runtime.reset();
            while(opModeIsActive() && runtime.milliseconds() < 1){
                continue;
            }

            telemetry.addData("(Y increase, A decrease, B accept)", "");
            telemetry.addData("3. Latch wait duration", latchDuration);
            telemetry.update();

            if (gamepad1.y) {
                latchDuration += incrementAmount;
            } else if (gamepad1.a) {
                latchDuration -= incrementAmount;
            }

            if(gamepad1.b) {
                optionLoop = false;
            }
        }

        while(gamepad1.b){
            continue;
        }

        optionLoop = true;

        while (optionLoop) {
            runtime.reset();
            while(opModeIsActive() && runtime.milliseconds() < 1){
                continue;
            }

            telemetry.addData("(Y increase, A decrease, B accept)", "");
            telemetry.addData("4. Reverse time", firstReverseDuration);
            telemetry.update();

            if (gamepad1.y) {
                firstReverseDuration += incrementAmount;
            } else if (gamepad1.a) {
                firstReverseDuration -= incrementAmount;
            }

            if(gamepad1.b) {
                optionLoop = false;
            }
        }

        while(gamepad1.b){
            continue;
        }

        optionLoop = true;

        while (optionLoop) {
            runtime.reset();
            while(opModeIsActive() && runtime.milliseconds() < 1){
                continue;
            }

            telemetry.addData("(Y increase, A decrease, B accept)", "");
            telemetry.addData("5. Turn left (score foundation) time", turnDuration);
            telemetry.update();

            if (gamepad1.y) {
                turnDuration += incrementAmount;
            } else if (gamepad1.a) {
                turnDuration -= incrementAmount;
            }

            if(gamepad1.b) {
                optionLoop = false;
            }
        }

        while(gamepad1.b){
            continue;
        }

        optionLoop = true;

        while (optionLoop) {
            runtime.reset();
            while(opModeIsActive() && runtime.milliseconds() < 1){
                continue;
            }

            telemetry.addData("(Y increase, A decrease, B accept)", "");
            telemetry.addData("7. Back up (park) time", parkDuration);
            telemetry.update();

            if (gamepad1.y) {
                parkDuration += incrementAmount;
            } else if (gamepad1.a) {
                parkDuration -= incrementAmount;
            }

            if(gamepad1.b) {
                optionLoop = false;
            }
        }

    }

    private void drive(double power, double heading, double rotation) {
        double frontLeftPower = -power * Math.sin(heading - Math.PI / 4) + rotation;
        double frontRightPower = power * Math.cos(heading - Math.PI / 4) - rotation;
        double backLeftPower = power * Math.cos(heading - Math.PI / 4) + rotation;
        double backRightPower = -power * Math.sin(heading - Math.PI / 4) - rotation;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }


}

