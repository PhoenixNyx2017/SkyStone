package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="mecnanum Teleop", group="Teleop")
public class MecanumBasic extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            double rotation = gamepad1.right_stick_x;
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;

            double power = Math.hypot(x,y);
            double theta = Math.atan2(y, x) - Math.PI /2;

            double frontLeftPower = - power * Math.sin(theta - Math.PI/4) + rotation;
            double frontRightPower = power * Math.cos(theta - Math.PI/4) - rotation;
            double backLeftPower = power * Math.cos(theta - Math.PI/4) + rotation;
            double backRightPower = - power * Math.sin(theta - Math.PI/4) - rotation;

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight .setPower(backRightPower);

        }


    }

}
