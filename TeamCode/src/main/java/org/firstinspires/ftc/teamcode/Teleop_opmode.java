package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
@Disabled
public abstract class Teleop_opmode extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor left_front = null;
    private DcMotor left_back = null;
    private DcMotor right_front = null;
    private DcMotor right_back = null;

    private double reset_angle = 0;
    private

    BNO055IMU imu;

    //ModernRoboticsI2cGyro gyro    = null; //adding gyro

    @Override
    public void init() {
        left_front = hardwareMap.get(DcMotor.class, "left_front");
        left_back = hardwareMap.get(DcMotor.class, "left_back");
        right_front = hardwareMap.get(DcMotor.class, "right_front");
        right_back = hardwareMap.get(DcMotor.class, "right_back");

        left_front.setDirection(DcMotor.Direction.FORWARD);
        left_back.setDirection(DcMotor.Direction.FORWARD);
        right_front.setDirection(DcMotor.Direction.FORWARD);
        right_back.setDirection(DcMotor.Direction.FORWARD);


        //integrated gyro
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        //double lf_power, lb_power, rf_power, rb_power;
        //double drive, strafe, rotate;
        //drive, strafe, rotate
        //-gamepad1.left_stick_y, gamepad1.right_stick_x
        //drive = -gamepad1.left_stick_y;
        //strafe = gamepad1.left_stick_x;

        //rotate = gamepad1.right_stick_x;
        //double pi_4 = Math.PI / 4;

        //lf_power = (drive *Math.sin(rotate + pi_4)) + strafe;
        //lb_power = (drive *Math.cos(rotate + pi_4)) - strafe;
        //rf_power = (drive *Math.cos(rotate + pi_4)) + strafe;
        //rb_power = (drive *Math.sin(rotate + pi_4)) - strafe;

        //double pos = BNO055IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ,
        //        BNO055IMU.AngleUnit.DEGREES);

        //left_front.setPower(lf_power);
        //right_front.setPower(rf_power);
        //left_back.setPower(lb_power);
        //right_back.setPower(rb_power);

        drive();
    }

    @Override
    public void stop() {
    }

    //https://seas.yale.edu/sites/default/files/imce/other/HolonomicOmniWheelDrive.pdf

    public void drive() {
        //This is for omni drive
        double Protate = gamepad1.right_stick_x/4;
        double stick_x = gamepad1.left_stick_x * Math.sqrt(Math.pow(1-Math.abs(Protate), 2)/2);
//Accounts for Protate when limiting magnitude to be less than 1
        double stick_y = gamepad1.left_stick_y * Math.sqrt(Math.pow(1-Math.abs(Protate), 2)/2);
        double theta = 0;
        double Px = 0;
        double Py = 0;
        double gyroAngle = getHeading() * Math.PI / 180; //Converts gyroAngle into radians

        if (gyroAngle <= 0) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (0 < gyroAngle && gyroAngle < Math.PI / 2) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (Math.PI / 2 <= gyroAngle) {
            gyroAngle = gyroAngle - (3 * Math.PI / 2);
        }
        gyroAngle = -1 * gyroAngle;

        if(gamepad1.right_bumper){
            //Disables gyro, sets to -Math.PI/2 so front is defined correctly.
                    gyroAngle = -Math.PI/2;
        }
//Linear directions in case you want to do straight lines.
        if(gamepad1.dpad_right){
            stick_x = 0.5;
        }
        else if(gamepad1.dpad_left){
            stick_x = -0.5;
        }
        if(gamepad1.dpad_up){
            stick_y = -0.5;
        }
        else if(gamepad1.dpad_down){
            stick_y = 0.5;
        }
//MOVEMENT
        theta = Math.atan2(stick_y, stick_x) - gyroAngle - (Math.PI / 2);
        Px = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) *
                (Math.sin(theta + Math.PI / 4));
        Py = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) *
                (Math.sin(theta - Math.PI / 4));

        left_front.setPower(Py - Protate);
        left_back.setPower(Px - Protate);
        right_back.setPower(Py + Protate);
        right_front.setPower(Px + Protate);

    }

    public void resetAngle() {
        if(gamepad1.a) {
            reset_angle = getHeading() + reset_angle;
        }
    }

    public double getHeading() {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES);
        double heading = angles.firstAngle;
        if (heading < -180) {
            heading = heading + 360;
        }
        else if(heading > 180) {
            heading = heading - 360;
        }
        heading = heading - reset_angle;
        return heading;

    }


}
