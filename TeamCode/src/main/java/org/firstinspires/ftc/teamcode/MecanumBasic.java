package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "mecanum Teleop", group = "Teleop")
public class MecanumBasic extends LinearOpMode {

    static final double ARM_UP = .5;
    static final double ARM_DOWN = -.5;
    private static final double RIC_OPEN = 0.0;
    private static final double RIC_CLOSE = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");

        //DcMotor armMotor = hardwareMap.dcMotor.get("armMotor");

        //Servo ric = hardwareMap.servo.get("ric");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        double ricPosition = 0;

        waitForStart();

        while (opModeIsActive()) {

            double rotation = gamepad1.right_stick_x;
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            //

            double power = Math.hypot(x, y);
            double theta = Math.atan2(y, x) - Math.PI / 2;

            double frontLeftPower = power * Math.cos(theta - Math.PI / 4) + rotation;
            double frontRightPower = -power * Math.sin(theta - Math.PI / 4) - rotation;
            double backLeftPower = -power * Math.sin(theta - Math.PI / 4) + rotation;
            double backRightPower = power * Math.cos(theta - Math.PI / 4) - rotation;


            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);

           /* if (gamepad1.right_bumper) {
                armMotor.setPower(ARM_UP);
            } else if (gamepad1.right_trigger > 0) {
                armMotor.setPower(ARM_DOWN);
            } else {
                armMotor.setPower(0);
            }

//            if (gamepad1.left_bumper) {
//                ric.setPosition(RIC_OPEN);
//            } else if (gamepad1.left_trigger > 0) {
//                ric.setPosition(RIC_CLOSE);
//            }

            if (gamepad1.left_bumper) {
                ricPosition -= 0.001;
                if (ricPosition < 0.4) {
                    ricPosition = 0.4;
                }
            } else if (gamepad1.left_trigger > 0) {
                ricPosition += 0.001;
                if (ricPosition > 0.7) {
                    ricPosition = 0.7;
                }
            }

            ric.setPosition(ricPosition);

            telemetry.addData("RIC position", ricPosition);
            telemetry.update();*/
        }
    }
}
