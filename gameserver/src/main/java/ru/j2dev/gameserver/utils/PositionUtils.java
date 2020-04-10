package ru.j2dev.gameserver.utils;

import ru.j2dev.gameserver.model.Creature;
import ru.j2dev.gameserver.model.GameObject;

public class PositionUtils {
    private static final int MAX_ANGLE = 360;
    private static final double FRONT_MAX_ANGLE = 100.0;
    private static final double BACK_MAX_ANGLE = 40.0;

    //Overworld
    public static TargetDirection getDirectionAt(final Creature target, final Creature attacker) {
        if (target == null || attacker == null) {
            return TargetDirection.NONE;
        }
        if (isBehind(target, attacker)) {
            return TargetDirection.BEHIND;
        }
        if (isInFrontOf(target, attacker)) {
            return TargetDirection.FRONT;
        }
        return TargetDirection.SIDE;
    }

    //L2GW
    public static TargetDirection getPosition(final Creature target, final Creature attacker) {
        if (target == null || attacker == null) {
            return TargetDirection.NONE;
        }
        int h = (int) (Math.atan2(target.getY() - attacker.getY(), target.getX() - attacker.getX()) * 0x1.fffep15 / (Math.PI * 2));
        h = Math.abs(target.getHeading() - h);

        if (h >= 0x2000 && h <= 0x6000 || h >= 0xa000 && h <= 0xe000) {
            return TargetDirection.SIDE;
        }
        if (h > 0x6000 && h < 0xa000) {
            return TargetDirection.FRONT;
        }

        return TargetDirection.BEHIND;
    }

    //L2JUnity
    public static TargetDirection getDirectionTo(final GameObject target, final GameObject attacker) {
        if (target == null || attacker == null) {
            return TargetDirection.NONE;
        }
        final int heading = Math.abs(target.getHeading() - calculateHeadingTo(attacker, target));
        if (((heading >= 0x2000) && (heading <= 0x6000)) || (Integer.toUnsignedLong(heading - 0xA000) <= 0x4000)) {
            return TargetDirection.SIDE;
        } else if (Integer.toUnsignedLong(heading - 0x2000) <= 0xC000) {
            return TargetDirection.FRONT;
        } else {
            return TargetDirection.BEHIND;
        }
    }

    private static int calculateHeadingTo(final GameObject from, final GameObject to) {
        return Short.toUnsignedInt((short) Math.floor(((Math.atan2(to.getY() - from.getY(), to.getX() - from.getX())) * 0x1.fffep15) / 0x1.921fb54442d18p2));
    }

    public static boolean isInFrontOf(final Creature target, final Creature attacker) {
        if (target == null) {
            return false;
        }
        final double angleTarget = calculateAngleFrom(target, attacker);
        final double angleChar = convertHeadingToDegree(target.getHeading());
        double angleDiff = angleChar - angleTarget;
        if (angleDiff <= -260.0) {
            angleDiff += MAX_ANGLE;
        }
        if (angleDiff >= 260.0) {
            angleDiff -= MAX_ANGLE;
        }
        return Math.abs(angleDiff) <= FRONT_MAX_ANGLE;
    }

    public static boolean isBehind(final Creature target, final Creature attacker) {
        if (target == null) {
            return false;
        }
        final double angleChar = calculateAngleFrom(attacker, target);
        final double angleTarget = convertHeadingToDegree(target.getHeading());
        double angleDiff = angleChar - angleTarget;
        if (angleDiff <= -320.0) {
            angleDiff += MAX_ANGLE;
        }
        if (angleDiff >= 320.0) {
            angleDiff -= MAX_ANGLE;
        }
        return Math.abs(angleDiff) <= BACK_MAX_ANGLE;
    }

    public static boolean isFacing(final Creature attacker, final GameObject target, final int maxAngle) {
        if (target == null) {
            return false;
        }
        final double maxAngleDiff = maxAngle / 2;
        final double angleTarget = calculateAngleFrom(attacker, target);
        final double angleChar = convertHeadingToDegree(attacker.getHeading());
        double angleDiff = angleChar - angleTarget;
        if (angleDiff <= -MAX_ANGLE + maxAngleDiff) {
            angleDiff += MAX_ANGLE;
        }
        if (angleDiff >= MAX_ANGLE - maxAngleDiff) {
            angleDiff -= MAX_ANGLE;
        }
        return Math.abs(angleDiff) <= maxAngleDiff;
    }

    public static boolean isFacing(final Location attackerLoc, final Location targetLoc, final int maxAngle) {
        if (attackerLoc == null) {
            return false;
        }
        final double maxAngleDiff = maxAngle / 2;
        final double angleTarget = calculateAngleFrom(attackerLoc.getX(), attackerLoc.getY(), targetLoc.getX(), targetLoc.getY());
        final double angleChar = convertHeadingToDegree(targetLoc.getH());
        double angleDiff = angleChar - angleTarget;
        if (angleDiff <= -MAX_ANGLE + maxAngleDiff) {
            angleDiff += MAX_ANGLE;
        }
        if (angleDiff >= MAX_ANGLE - maxAngleDiff) {
            angleDiff -= MAX_ANGLE;
        }
        return Math.abs(angleDiff) <= maxAngleDiff;
    }

    public static int calculateHeadingFrom(final GameObject obj1, final GameObject obj2) {
        return calculateHeadingFrom(obj1.getX(), obj1.getY(), obj2.getX(), obj2.getY());
    }

    public static int calculateHeadingFrom(final int obj1X, final int obj1Y, final int obj2X, final int obj2Y) {
        double angleTarget = Math.toDegrees(Math.atan2(obj2Y - obj1Y, obj2X - obj1X));
        if (angleTarget < 0.0) {
            angleTarget += MAX_ANGLE;
        }
        return (int) (angleTarget * 182.044444444);
    }

    public static double calculateAngleFrom(final GameObject obj1, final GameObject obj2) {
        return calculateAngleFrom(obj1.getX(), obj1.getY(), obj2.getX(), obj2.getY());
    }

    public static double calculateAngleFrom(final int obj1X, final int obj1Y, final int obj2X, final int obj2Y) {
        double angleTarget = Math.toDegrees(Math.atan2(obj2Y - obj1Y, obj2X - obj1X));
        if (angleTarget < 0.0) {
            angleTarget += MAX_ANGLE;
        }
        return angleTarget;
    }

    public static boolean checkIfInRange(final int range, final int x1, final int y1, final int x2, final int y2) {
        return checkIfInRange(range, x1, y1, 0, x2, y2, 0, false);
    }

    public static boolean checkIfInRange(final int range, final int x1, final int y1, final int z1, final int x2, final int y2, final int z2, final boolean includeZAxis) {
        final long dx = x1 - x2;
        final long dy = y1 - y2;
        if (includeZAxis) {
            final long dz = z1 - z2;
            return dx * dx + dy * dy + dz * dz <= range * range;
        }
        return dx * dx + dy * dy <= range * range;
    }

    public static boolean checkIfInRange(final int range, final GameObject obj1, final GameObject obj2, final boolean includeZAxis) {
        return obj1 != null && obj2 != null && checkIfInRange(range, obj1.getX(), obj1.getY(), obj1.getZ(), obj2.getX(), obj2.getY(), obj2.getZ(), includeZAxis);
    }

    public static double convertHeadingToDegree(final int heading) {
        return heading / 182.044444444;
    }

    public static double convertHeadingToRadian(final int heading) {
        return Math.toRadians(convertHeadingToDegree(heading) - 90.0);
    }

    public static int convertDegreeToClientHeading(double degree) {
        if (degree < 0.0) {
            degree += MAX_ANGLE;
        }
        return (int) (degree * 182.044444444);
    }

    public static double calculateDistance(final int x1, final int y1, final int z1, final int x2, final int y2) {
        return calculateDistance(x1, y1, 0, x2, y2, 0, false);
    }

    public static double calculateDistance(final Location locA, final Location locB, final boolean includeZAxis) {
        return calculateDistance(locA.getX(), locA.getY(), locA.getZ(), locB.getX(), locB.getY(), locB.getZ(), includeZAxis);
    }

    public static double calculateDistance(final int x1, final int y1, final int z1, final int x2, final int y2, final int z2, final boolean includeZAxis) {
        final long dx = x1 - x2;
        final long dy = y1 - y2;
        if (includeZAxis) {
            final long dz = z1 - z2;
            return Math.sqrt(dx * dx + dy * dy + dz * dz);
        }
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double calculateDistance(final GameObject obj1, final GameObject obj2, final boolean includeZAxis) {
        if (obj1 == null || obj2 == null) {
            return 2.147483647E9;
        }
        return calculateDistance(obj1.getX(), obj1.getY(), obj1.getZ(), obj2.getX(), obj2.getY(), obj2.getZ(), includeZAxis);
    }

    public static double getDistance(final GameObject a1, final GameObject a2) {
        return getDistance(a1.getX(), a2.getY(), a2.getX(), a2.getY());
    }

    public static double getDistance(final Location loc1, final Location loc2) {
        return getDistance(loc1.getX(), loc1.getY(), loc2.getX(), loc2.getY());
    }

    public static double getDistance(final int x1, final int y1, final int x2, final int y2) {
        return Math.hypot(x1 - x2, y1 - y2);
    }

    public static int getHeadingTo(final GameObject actor, final GameObject target) {
        if (actor == null || target == null || target == actor) {
            return -1;
        }
        return getHeadingTo(actor.getLoc(), target.getLoc());
    }

    public static int getHeadingTo(final Location actor, final Location target) {
        if (actor == null || target == null || target.equals(actor)) {
            return -1;
        }
        final int dx = target.x - actor.x;
        final int dy = target.y - actor.y;
        int heading = target.h - (int) (Math.atan2(-dy, -dx) * 10430.378350470453 + 32768.0);
        if (heading < 0) {
            heading = (heading + 1 + Integer.MAX_VALUE & 0xFFFF);
        } else if (heading > 65535) {
            heading &= 0xFFFF;
        }
        return heading;
    }

    public enum TargetDirection {
        NONE,
        FRONT,
        SIDE,
        BEHIND
    }
}
