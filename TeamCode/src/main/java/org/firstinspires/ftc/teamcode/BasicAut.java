package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="basic Auto", group="Autonomous")
public class BasicAut extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        runtime.reset();
        while(opModeIsActive() && runtime.time() < 1.75){

            double rotation = 0;
            double y = -1;
            double x = 0;

            double power = Math.hypot(x,y);
            double theta = Math.atan2(y, x) - Math.PI /2;

            double frontLeftPower = power * Math.sin(theta - Math.PI/4) + rotation;
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
