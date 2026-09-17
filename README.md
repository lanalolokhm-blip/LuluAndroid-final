# لولو — نسخة مصححة

مشروع Android تعليمي للأطفال باللغة العربية (Jetpack Compose).

## الإصدار 1.2

### الإصلاحات في هذه النسخة:
1. **إصلاح خطأ الترجمة**: إضافة الـ import الناقص `LocalLayoutDirection` و `LayoutDirection`.
2. **إضافة Gradle Wrapper**: ملفات `gradlew` + `gradle/wrapper` عشان المشروع يشتغل على أي جهاز أو على GitHub بدون تثبيت Gradle مسبقاً.
3. **إضافة GitHub Actions**: workflow جاهز يبني الـ APK تلقائياً عند كل push.

### طريقة التشغيل على GitHub:
1. ارفع كل الملفات دي على Repository جديد (أو موجود).
2. روح على تبويب **Actions** → هتلاقي workflow اسمه **Build APK**.
3. بعد ما يخلص البناء، هتلاقي الـ APK في **Artifacts**.

### البناء المحلي:
```bash
chmod +x gradlew
./gradlew assembleDebug
```
الـ APK هيبقى في: `app/build/outputs/apk/debug/`

### ملاحظات:
- المشروع يستخدم Kotlin 2.0 + Compose + AGP 8.7.3
- RTL مفعل للعربية
- minSdk = 24
