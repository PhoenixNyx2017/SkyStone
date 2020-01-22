package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Servo Setpoint Test", group = "Teleop")
public class ServoSetpointTest extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    private Servo latchLeft;
    private Servo latchRight;
    private double latchLeftPosition = 0.0;
    private double latchRightPosition = 0.0;

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

            if (gamepad2.y || gamepad1.y) {
                latchRightPosition += 0.0001;
                if (latchRightPosition > 1) {
                    latchRightPosition = 1;
                }
            }
            if (gamepad2.a || gamepad1.a) {
                latchRightPosition -= 0.0001;
                if (latchRightPosition < 0) {
                    latchRightPosition = 0;
                }
            }

            if (gamepad2.dpad_up || gamepad1.dpad_up) {
                latchLeftPosition += 0.0001;
                if (latchLeftPosition > 1) {
                    latchLeftPosition = 1;
                }
            }
            if (gamepad2.dpad_down || gamepad1.dpad_down) {
                latchLeftPosition -= 0.0001;
                if (latchLeftPosition < 0) {
                    latchLeftPosition = 0;
                }
            }

            latchLeft.setPosition(latchLeftPosition);
            latchRight.setPosition(latchRightPosition);

            telemetry.addData("Latch left (dpad up/down)", latchLeftPosition);
            telemetry.addData("Latch right (y/a)", latchRightPosition);
            telemetry.update();
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
