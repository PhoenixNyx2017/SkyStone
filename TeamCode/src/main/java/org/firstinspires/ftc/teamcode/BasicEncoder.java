package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Disabled
public class BasicEncoder extends LinearOpMode {

    static final double COUNTS_PER_REV = 1440;
    static final double WHEEL_DIAMETER_IN = 2;

    private DcMotor leftDrive;
    private DcMotor rightDrive;

    public void runOpMode() {

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //waitForStart(); // only for auto mode

        if (opModeIsActive()) {

        }

    }
}
