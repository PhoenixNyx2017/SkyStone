package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
@Disabled
public class BasicOmni extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor front = null; //will need to add actual variables to it
    private DcMotor back = null;
    private DcMotor right = null;
    private DcMotor left = null;

    @Override
    public void runOpMode() throws InterruptedException {

        while(opModeIsActive()) {
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double rotate = gamepad1.right_stick_x;

            drive(x,y,rotate);

        }
    }

    private void drive(double angle, double power, double rotate) {
        double frontPow = power * Math.cos(angle) + rotate;
        double backPow = power * Math.cos(angle) + rotate;
        double rightPow = power * Math.cos(angle) + rotate;
        double leftPow = power * Math.cos(angle) + rotate;

        front.setPower(frontPow);
        back.setPower(backPow);
        left.setPower(rightPow);
        right.setPower(leftPow);
    }
}
