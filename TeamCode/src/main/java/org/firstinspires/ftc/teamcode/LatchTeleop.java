package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Latch Teleop", group = "Teleop")
public class LatchTeleop extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

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

        waitForStart();

        while (opModeIsActive()) {
            double rotation = gamepad1.right_stick_x;
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;

            double power = Math.hypot(x, y);
            double theta = Math.atan2(y, x) - Math.PI / 2;

            drive(power, theta, rotation);

            // Move latches to "Release" position
            if (gamepad2.y  || gamepad1.y) {
                latchRight.setPosition(latchRightRelease);
                latchLeft.setPosition(latchLeftRelease);
            }
            // Move latches to "Stone" position
            if (gamepad2.b || gamepad1.b) {
                latchRight.setPosition(latchRightStone);
                latchLeft.setPosition(latchLeftStone);
            }
            // Move latches to "Foundation" position;
            if (gamepad2.a || gamepad1.a) {
                latchRight.setPosition(latchRightFoundation);
                latchLeft.setPosition(latchLeftFoundation);
            }
        }
    }

    private void drive(double power, double theta, double rotation) {
        double frontLeftPower = -power * Math.sin(theta - Math.PI / 4) + rotation;
        double frontRightPower = power * Math.cos(theta - Math.PI / 4) - rotation;
        double backLeftPower = power * Math.cos(theta - Math.PI / 4) + rotation;
        double backRightPower = -power * Math.sin(theta - Math.PI / 4) - rotation;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }
}
