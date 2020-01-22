package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="UpLeft", group="Auto")
public class UpLeftAuto extends LinearOpMode {
    private ElapsedTime     runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");

        DcMotor armMotor = hardwareMap.dcMotor.get("armMotor");

        Servo ric = hardwareMap.servo.get("ric");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //double ricPosition = 0;
        runtime.reset();

        waitForStart();

        //-----------------------------------------------------
        //drives up for 2 seconds
        double rotation = 0;
        double y = 1; //-y goes up
        double x = 0;
        //

        double power = Math.hypot(x, y);
        double theta = Math.atan2(y, x) - Math.PI / 2;

        double frontLeftPower = power * Math.cos(theta - Math.PI / 4) + rotation;
        double frontRightPower = -power * Math.sin(theta - Math.PI / 4) - rotation;
        double backLeftPower = -power * Math.sin(theta - Math.PI / 4) + rotation;
        double backRightPower = power * Math.cos(theta - Math.PI / 4) - rotation;

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.75) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        }

        rotation = 1;
        y = 0;
        x = 0;

        power = Math.hypot(x, y);
        theta = Math.atan2(y, x) - Math.PI / 2;

        frontLeftPower = power * Math.cos(theta - Math.PI / 4) + rotation;
        frontRightPower = -power * Math.sin(theta - Math.PI / 4) - rotation;
        backLeftPower = -power * Math.sin(theta - Math.PI / 4) + rotation;
        backRightPower = power * Math.cos(theta - Math.PI / 4) - rotation;

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.7) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        }
        //--------------------------------
        // drives strait for 2.5 seconds <- from here to
        rotation = 0; // -1 goes counter cw, 1 goes clockwise
        y = 1; //-y goes up
        x = 0;
        /* basically for each segment at the moment you want ot only have one of the above variables
        set to a numter other than 0, then for time, set some time, start with 1 sec and observe how much
        the robot will move in that direction
         */
        power = Math.hypot(x, y);
        theta = Math.atan2(y, x) - Math.PI / 2;

        frontLeftPower = power * Math.cos(theta - Math.PI / 4) + rotation;
        frontRightPower = -power * Math.sin(theta - Math.PI / 4) - rotation;
        backLeftPower = -power * Math.sin(theta - Math.PI / 4) + rotation;
        backRightPower = power * Math.cos(theta - Math.PI / 4) - rotation;

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.75) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        } // <- here is where you determine the 'path' segments for your whole autonmous for this run
        /* so it's like going straigt for like 1 sec is one segment
        then you turn for 2 secs, that is the second segment, basically it's like breaking
        the whole path that you want to travl */
        //-------------------------------------------------------------

    }


}
