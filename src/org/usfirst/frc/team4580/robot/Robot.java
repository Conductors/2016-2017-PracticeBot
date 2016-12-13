package org.usfirst.frc.team4580.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//Need to declare Talons using CANTalon data type ASAP
	CameraServer camera;
	RobotDrive myRobot;
	Joystick stick;
	int autoLoopCounter;
    boolean joystickA;
	boolean joystickB;
	boolean joystickX;
	boolean joystickY;
	boolean joystickLSB;
	boolean joystickRSB;
	boolean joystickLB;
	boolean joystickRB;
	double joystickLSX;
	double joystickLSY;
	double joystickRSX;
	double joystickRSY;
	double joyLeftOut;;
	double joyRightOut;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//Sets left motor to channel #0, right motor to #1
    	myRobot = new RobotDrive(0,1);
    	//Sets stick equal to joystick at port #0
    	stick = new Joystick(0);
        //Sets up camera
    	camera = CameraServer.getInstance();
        camera.startAutomaticCapture("cam0");
    	camera.setQuality(100);
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if(autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
		{
			myRobot.drive(-0.5, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
			} else {
			myRobot.drive(0.0, 0.0); 	// stop robot
		}
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	//Refreshes input
    	updateInput();
        //Displays input values to smart dashboard
    	smartDashboard();
        driveBot();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
   public void driveBot() {
	   // Sets tank drive equal to joystick values
	   //May have to set to negatives, double check in dashboard
	   joyLeftOut = joystickRSY;
	   joyRightOut = joystickRSY;
	   myRobot.tankDrive(joyLeftOut, joyRightOut, true);

   }
    public void updateInput() {
        //Refreshes values from joystick and later, sensors
    	joystickA = stick.getRawButton(2);
        joystickB = stick.getRawButton(3);
    	joystickX = stick.getRawButton(1);
    	joystickY = stick.getRawButton(4);
    	joystickLSB = stick.getRawButton(11);
    	joystickRSB = stick.getRawButton(12);
    	joystickLB = stick.getRawButton(5);
    	joystickRB = stick.getRawButton(6);
    	joystickLSX = stick.getRawAxis(0);
    	joystickLSY = stick.getRawAxis(1);
    	joystickRSX = stick.getRawAxis(2);
    	joystickRSY = stick.getRawAxis(3);
    }
    public void smartDashboard(){
        SmartDashboard.putNumber("Left Stick", joystickLSY);
		SmartDashboard.putNumber("Right Stick", joystickRSY);
    }
}
