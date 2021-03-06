// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.AdjusterConstants;
import frc.robot.Constants.ControllerButtons;
import frc.robot.Constants.ShootButtons;
import frc.robot.Constants.TestConstants;
import frc.robot.Constants.TestButtons;
import frc.robot.commands.CollectorRun;
import frc.robot.commands.DriveMech;
import frc.robot.commands.FireCheck;
import frc.robot.commands.FullCollect;
//import frc.robot.commands.LiftDown;
import frc.robot.commands.ManualAdjust;
import frc.robot.commands.IndexCheck;
import frc.robot.commands.ManualTurret;
import frc.robot.commands.NewAutoTurret;
//import frc.robot.commands.PrimeHanger;
import frc.robot.commands.PrimingSequence;
import frc.robot.commands.ResetAdjuster;
import frc.robot.commands.SetAdjuster;
import frc.robot.commands.ShooterRun;
import frc.robot.subsystems.Adjuster;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Flopper;
import frc.robot.subsystems.Indexer;
//import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MechTrain;
import frc.robot.subsystems.MrMills;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
//import frc.robot.subsystems.Winch;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final Lifter m_Lifter = new Lifter();
  // private final Reacher m_Reacher = new Reacher();
  private final MechTrain m_Drive = new MechTrain();
  //private final Winch m_Winch = new Winch();
  private final Collector m_Collector = new Collector();
  private final Shooter m_Shooter = new Shooter();
  private final Turret m_Turret = new Turret();
  private final Indexer m_Indexer = new Indexer();
  private final Limelight m_Limelight = new Limelight();
  private final MrMills m_Mills = new MrMills();
  private final Flopper m_Flopper = new Flopper();
  //private final Lifter m_Lifter = new Lifter();
  private final Adjuster m_Adjuster = new Adjuster();
  private final Joystick driveStick = new Joystick(0);
  private final Joystick shootStick = new Joystick(1);
  private final XboxController driveController = new XboxController(0);
  //private final Command Autonomous1 = new Autonomous1(m_Drive, m_Collector, m_Shooter, m_Indexer, m_Mills, m_Turret, m_Limelight, m_Flopper, m_Lifter);
  //private final Command SimpleAuton = new SimpleAuton(m_Drive);
  //private final Command DriveBug = new DriveBug(m_Limelight, m_Mills, m_Shooter);
  //private final Command Autonomous2 = new Autonomous2(m_Drive, m_Collector, m_Shooter, m_Indexer, m_Mills, m_Turret, m_Limelight, m_Flopper, m_Lifter);
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  SendableChooser<Command> debugChooser = new SendableChooser<>();
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    //sets the drive to the default of driving normally
    m_Drive.setDefaultCommand(new DriveMech(m_Drive, driveController::getLeftX, driveController::getLeftY, driveController::getRightX));
        // m_Drive.setDefaultCommand(new DriveMech(m_Drive, driveStick::getX, driveStick::getY, driveStick::getZ));
        //m_Winch.setDefaultCommand(new WinchRun(m_Winch, driveController::getRightTriggerAxis, driveController::getLeftTriggerAxis));
    // m_Adjuster.setDefaultCommand(new SetAdjuster(m_Adjuster, AdjusterConstants.distAngle));
    // m_Collector.setDefaultCommand(new CollectorFlop(m_Collector, shootStick.getRawButton(ShootButtons.collect), m_Flopper));
    // m_Reacher.setDefaultCommand(new ReachDown(m_Reacher));
    // m_Lifter.setDefaultCommand(new LiftDown(m_Lifter));
    //sets the turret to track the hub automatically
    // m_Turret.setDefaultCommand(new AutoTurret(m_Turret, m_Limelight, false));

    // Add commands to the autonomous command chooser
    //m_chooser.setDefaultOption("Auton1", Autonomous1);
    //m_chooser.addOption("Auton2", Autonomous2);
    //m_chooser.addOption("SimpleAuton", SimpleAuton);

    //debugChooser.setDefaultOption("Debug Chooser", DriveBug);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(shootStick, 2).whenPressed(new ResetAdjuster(m_Adjuster));
    //sets all of the commands to the appropriate buttons
    new JoystickButton(shootStick, ShootButtons.collectOn).whileHeld(new FullCollect(m_Collector, m_Flopper, TestConstants.collectF));
    //  new JoystickButton(shootStick, ShootButtons.simpleCollect).whileHeld(new CollectorRun(m_Collector, TestConstants.collectF));
    new JoystickButton(shootStick, ShootButtons.backCollect).whileHeld(new CollectorRun(m_Collector, TestConstants.collectB));
    new JoystickButton(shootStick, ShootButtons.prime).whenPressed(new PrimingSequence(m_Collector, m_Indexer, m_Mills, m_Shooter));
    new JoystickButton(shootStick, ShootButtons.shooterOff).whenPressed(new ShooterRun(m_Shooter, 0, false));
    new JoystickButton(shootStick, ShootButtons.manualTurret).whenPressed(new ManualTurret(m_Turret, shootStick::getX));
    new JoystickButton(shootStick, ShootButtons.autoTurret).whenPressed(new NewAutoTurret(m_Turret, m_Limelight));
    new JoystickButton(shootStick, ShootButtons.manualAdjust).whenPressed(new ManualAdjust(m_Adjuster, shootStick::getY));
    new JoystickButton(shootStick, ShootButtons.autoAdjust).whenPressed(new SetAdjuster(m_Adjuster, AdjusterConstants.distAngle));

    //  new JoystickButton(shootStick, ShootButtons.auto).whenPressed(new AutoTurret(m_Turret, m_Limelight, false));
    //  new JoystickButton(shootStick, ShootButtons.backDex).whileHeld(new IndexerRun(m_Indexer, -.15));
    new JoystickButton(shootStick, ShootButtons.index).whileHeld(new IndexCheck(m_Indexer, m_Collector, m_Mills));
    // new JoystickButton(shootStick, ShootButtons.primeHang).whenPressed(new PrimeHanger(m_Turret, m_Lifter));
    // new JoystickButton(shootStick, ShootButtons.unprimeHang).whenPressed(new LiftDown(m_Lifter));

    new JoystickButton(driveStick, TestButtons.fire).whenPressed(new FireCheck(m_Indexer, m_Collector, m_Mills, m_Shooter));
    new JoystickButton(driveController, ControllerButtons.pressFire).whenPressed(new FireCheck(m_Indexer, m_Collector, m_Mills, m_Shooter));
     //new JoystickButton(driveController, ControllerButtons.stop).whileHeld(new Stop(m_Shooter, m_Collector, m_Winch, m_Indexer, m_Turret, m_Flopper));
      //new JoystickButton(driveController, ControllerButtons.winchAutoUp).whileHeld(new SetWinch(m_Winch, ClimberConstants.autoDistance));
    //  new JoystickButton(driveController, ControllerButtons.winchReset).whenPressed(new ResetWinch(m_Winch));
  }

  public Command getDebugMode(){
    return debugChooser.getSelected();
  }

  public Command getAutonomousCommand() {
    //sets the autonomous to the one selected in the shuffleboard
    return m_chooser.getSelected();
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}