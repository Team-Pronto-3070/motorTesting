/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3070.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {

	TalonSRX tal1, tal2;
	DigitalInput limit;
	Joystick joy;
	double motorValue = 0;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		tal1 = new TalonSRX(0);
		tal2 = new TalonSRX(1);
		limit = new DigitalInput(0);
		joy = new Joystick(0);
		SmartDashboard.putNumber("Motor value", motorValue);
		SmartDashboard.putBoolean("switch", limit.get());
		tal2.setInverted(true);
		tal1.setInverted(false);
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString line to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the SendableChooser
	 * make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		motorValue = SmartDashboard.getNumber("Motor value", 0.2);
		SmartDashboard.putBoolean("switch", limit.get());
		if (joy.getRawAxis(1) < -0.5){
			tal1.set(ControlMode.PercentOutput, -0.5);
			tal2.set(ControlMode.PercentOutput, -0.5);
		} else if (limit.get()) {
			tal1.set(ControlMode.PercentOutput, 0);
			tal2.set(ControlMode.PercentOutput, 0);
		} else if (joy.getRawAxis(1) > 0.5) {
			tal1.set(ControlMode.PercentOutput, 0.5);
			tal2.set(ControlMode.PercentOutput, 0.5);
		} else{
			tal1.set(ControlMode.PercentOutput, 0);
			tal2.set(ControlMode.PercentOutput, 0);
		}

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
