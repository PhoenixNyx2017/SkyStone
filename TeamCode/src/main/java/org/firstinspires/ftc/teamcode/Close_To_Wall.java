package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Close To Wall", group="s")
public class Close_To_Wall extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;
    private DcMotor linslidemot = null;
    private DcMotor clawmot = null;

    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime runtime = new ElapsedTime();
        frontLeft  = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRight = hardwareMap.get(DcMotor.class, "front_right_drive");
        backRight  = hardwareMap.get(DcMotor.class, "back_right_drive");
        backLeft = hardwareMap.get(DcMotor.class, "back_left_drive");
        linslidemot = hardwareMap.get(DcMotor.class, "linear_slide_motor");
        clawmot = hardwareMap.get(DcMotor.class, "claw_motor");

        Servo servo = hardwareMap.get(Servo.class, "left_hand");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        drive(Math.PI, 1,  0);
        runtime.reset();
        while(opModeIsActive() && runtime.milliseconds() < 1500){
            // do nothing
        }
        drive(0,0,0);


    }
    private void drive(double direction, double speed, double rotation){
        direction += Math.PI / 4.0;
        double f_l_power = speed * Math.cos(direction) - rotation;
        double f_r_power = speed * Math.sin(direction) + rotation;
        double b_l_power = speed * Math.sin(direction) - rotation;
        double b_r_power = speed * Math.cos(direction) + rotation;

        frontLeft.setPower(f_l_power);
        frontRight.setPower(f_r_power);
        backLeft.setPower(b_l_power);
        backRight.setPower(b_r_power);
    }
}
