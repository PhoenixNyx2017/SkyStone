package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Block Auto", group="Auto")
public class BlockAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");

        //DcMotor armMotor = hardwareMap.dcMotor.get("armMotor");

        //Servo ric = hardwareMap.servo.get("ric");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //double ricPosition = 0;
        runtime.reset();

        waitForStart();

        //-----------------------------------------------------
        //drives straight for 2 seconds
        double rotation = 0;
        double y = 1;
        double x = 0;
        //

        double power = Math.hypot(x, y);
        double theta = Math.atan2(y, x) - Math.PI / 2;

        double frontLeftPower = power * Math.cos(theta - Math.PI / 4) + rotation;
        double frontRightPower = -power * Math.sin(theta - Math.PI / 4) - rotation;
        double backLeftPower = -power * Math.sin(theta - Math.PI / 4) + rotation;
        double backRightPower = power * Math.cos(theta - Math.PI / 4) - rotation;

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.0) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        }

        //-------------------------------------------------------------
        //-----------------------------------------------------
        //drives straight for 2 seconds
        rotation = 1;
        y = 0;
        x = 0;
        //

        power = Math.hypot(x, y);
        theta = Math.atan2(y, x) - Math.PI / 2;

        frontLeftPower = power * Math.cos(theta - Math.PI / 4) + rotation;
        frontRightPower = -power * Math.sin(theta - Math.PI / 4) - rotation;
        backLeftPower = -power * Math.sin(theta - Math.PI / 4) + rotation;
        backRightPower = power * Math.cos(theta - Math.PI / 4) - rotation;

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.25) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        }

        //-------------------------------------------------------------
        //-----------------------------------------------------
        //drives straight for 2 seconds
        rotation = 1;
        y = 0;
        x = 0;
        //

        power = Math.hypot(x, y);
        theta = Math.atan2(y, x) - Math.PI / 2;

        frontLeftPower = power * Math.cos(theta - Math.PI / 4) + rotation;
        frontRightPower = -power * Math.sin(theta - Math.PI / 4) - rotation;
        backLeftPower = -power * Math.sin(theta - Math.PI / 4) + rotation;
        backRightPower = power * Math.cos(theta - Math.PI / 4) - rotation;

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.25) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        }

        //-------------------------------------------------------------
        //-----------------------------------------------------
        //drives straight for 2 seconds
        rotation = 1;
        y = 0;
        x = 0;
        //

        power = Math.hypot(x, y);
        theta = Math.atan2(y, x) - Math.PI / 2;

        frontLeftPower = power * Math.cos(theta - Math.PI / 4) + rotation;
        frontRightPower = -power * Math.sin(theta - Math.PI / 4) - rotation;
        backLeftPower = -power * Math.sin(theta - Math.PI / 4) + rotation;
        backRightPower = power * Math.cos(theta - Math.PI / 4) - rotation;

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.25) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        }

        //-------------------------------------------------------------
        //-----------------------------------------------------
        //drives straight for 2 seconds
        rotation = 1;
        y = 0;
        x = 0;
        //

        power = Math.hypot(x, y);
        theta = Math.atan2(y, x) - Math.PI / 2;

        frontLeftPower = power * Math.cos(theta - Math.PI / 4) + rotation;
        frontRightPower = -power * Math.sin(theta - Math.PI / 4) - rotation;
        backLeftPower = -power * Math.sin(theta - Math.PI / 4) + rotation;
        backRightPower = power * Math.cos(theta - Math.PI / 4) - rotation;

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.25) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        }

        //-------------------------------------------------------------
        //-----------------------------------------------------
        //drives straight for 2 seconds
        rotation = 1;
        y = 0;
        x = 0;
        //

        power = Math.hypot(x, y);
        theta = Math.atan2(y, x) - Math.PI / 2;

        frontLeftPower = power * Math.cos(theta - Math.PI / 4) + rotation;
        frontRightPower = -power * Math.sin(theta - Math.PI / 4) - rotation;
        backLeftPower = -power * Math.sin(theta - Math.PI / 4) + rotation;
        backRightPower = power * Math.cos(theta - Math.PI / 4) - rotation;

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.25) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        }

        //-------------------------------------------------------------


    }


}

