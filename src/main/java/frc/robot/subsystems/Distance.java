package frc.robot.subsystems;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.RangeProfile;
import com.revrobotics.Rev2mDistanceSensor.Unit;

public class Distance {
    Rev2mDistanceSensor mrMills = new Rev2mDistanceSensor(com.revrobotics.Rev2mDistanceSensor.Port.kMXP);
    double ballDist = mrMills.getRange(Unit.kInches);
    double collectDistLow = 4;
    double collectDistHigh = 12;
    double indexDist = 4;
    

}
