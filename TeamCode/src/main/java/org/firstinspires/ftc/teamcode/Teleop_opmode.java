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
        double lf_power, lb_power, rf_power, rb_power;
        double drive, strafe, rotate;
        //drive, strafe, rotate
        //-gamepad1.left_stick_y, gamepad1.right_stick_x
        drive = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;

        rotate = gamepad1.right_stick_x;
        double pi_4 = Math.PI / 4;


        //double lf = drive + strafe + rotate;
        //double lb = drive - strafe + rotate;
        //double rf = drive - strafe - rotate;
        //double rb = drive + strafe - rotate;

        lf_power = (drive *Math.sin(rotate + pi_4)) + strafe;
        lb_power = (drive *Math.cos(rotate + pi_4)) - strafe;
        rf_power = (drive *Math.cos(rotate + pi_4)) + strafe;
        rb_power = (drive *Math.sin(rotate + pi_4)) - strafe;

        //double pos = BNO055IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ,
        //        BNO055IMU.AngleUnit.DEGREES);

        left_front.setPower(lf_power);
        right_front.setPower(rf_power);
        left_back.setPower(lb_power);
        right_back.setPower(rb_power);

    }

    @Override
    public void stop() {
    }


}
