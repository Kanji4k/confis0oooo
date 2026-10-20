package client.confis;

/** Centralized, allocation-free palette for Edit Hub's 12 solid and 12 glass themes. */
public final class ThemeManager {
    private ThemeManager() {}
    public static final String[] IDS = {"BLACK", "WHITE", "YELLOW", "RED", "BLUE", "PURPLE", "BROWN", "GREY", "PINK", "GREEN", "LIGHT_BLUE", "ORANGE"};
    public static final String[] LABELS = {"Black", "White", "Yellow", "Red", "Blue", "Purple", "Brown", "Grey", "Pink", "Green", "Light Blue", "Orange"};
    private static final int[] COLORS = {0x202124, 0xE8EDF2, 0xE9C840, 0xD94452, 0x2684E8, 0x8954D6, 0x8B5E3C, 0x858D98, 0xE87EAD, 0x2BAE66, 0x52BDEB, 0xEF8A32};
    public static boolean isValidColor(String id) { if (id == null) return false; for (String value : IDS) if (value.equalsIgnoreCase(id)) return true; return false; }
    public static int index(String id) { if (id != null) for (int i=0;i<IDS.length;i++) if (IDS[i].equalsIgnoreCase(id)) return i; return 4; }
    public static int color(String id) { return COLORS[index(id)]; }
    public static int colorAt(int i) { return COLORS[Math.max(0, Math.min(COLORS.length-1, i))]; }
    public static String idAt(int i) { return IDS[Math.max(0, Math.min(IDS.length-1, i))]; }
    public static String labelAt(int i) { return LABELS[Math.max(0, Math.min(LABELS.length-1, i))]; }
    public static float clamp(float value, float min, float max) { if (Float.isNaN(value) || Float.isInfinite(value)) return min; return Math.max(min, Math.min(max, value)); }
    public static boolean glass() { return "GLASS".equalsIgnoreCase(Config.get().backgroundMode); }
    public static int alpha(float opacity) { return Math.max(0x26, Math.min(0xF2, Math.round(clamp(opacity, 0.15f, 0.95f)*255f))); }
    public static int argb(int alpha, int rgb) { return (Math.max(0, Math.min(255, alpha)) << 24) | (rgb & 0xFFFFFF); }
    public static int mix(int a, int b, float t) { t=clamp(t,0f,1f); int r=Math.round(((a>>16)&255)*(1f-t)+((b>>16)&255)*t); int g=Math.round(((a>>8)&255)*(1f-t)+((b>>8)&255)*t); int bl=Math.round((a&255)*(1f-t)+(b&255)*t); return (r<<16)|(g<<8)|bl; }
}
