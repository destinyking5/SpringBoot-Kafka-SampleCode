package com.pluralsight.conference;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class VersionTests {

    @Test
    public void testNullVersionException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Version(null);
                }).withMessage("'version' must not be null!");
    }

    @Test
    public void testVersionRegexNotMatchException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Version("10.2.0-SNAP1SHOT");
                }).withMessage("'version' must match: 'major.minor.patch(-SNAPSHOT)'!");
    }

    @Test //3
    public void testFormatMatch() {
        String regex = "\\d+(\\.\\d+){0,2}(-SNAPSHOT)?";

        String s0 ="0.1";
        Assert.assertTrue(s0.matches(regex));

        String s1 ="10.0.0";
        Assert.assertTrue(s1.matches(regex));

        String s2 ="10.2.0";
        Assert.assertTrue(s2.matches(regex));

        String s3 ="10.2.1";
        Assert.assertTrue(s3.matches(regex));

        String s4 ="10.2.1-SNAPSHOT";
        Assert.assertTrue(s4.matches(regex));
    }

    @Test //4
    public void testVersionRegexMatchesWithNoException() {
        Version v1 = new Version("10.0.0");
        Version v2 = new Version("10.2.0");
        Version v3 = new Version("10.2.1");
        Version v4 = new Version("10.2.1-SNAPSHOT");
    }

    @Test //5_1
    public void testIsSNAPSHOT() {
        Version vSnapshot = new Version("10.2.1-SNAPSHOT");
        Assert.assertTrue(vSnapshot.isSnapshot());
    }

    @Test //5_2
    public void testIsNotSNAPSHOT() {
        Version vSnapshot = new Version("10.2.1");
        Assert.assertFalse(vSnapshot.isSnapshot());
    }

    @Test // 6_1
    public void testNullVersionComparison() {
        Version a = new Version("1.1");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    a.compareTo(null);
                }).withMessage("'other' must not be null!");
    }

    @Test // 6_2
    public void testEqualVersionComparison() {
        Version a = new Version("1.1.1");
        Version b = new Version("1.1.1");
        Assert.assertEquals(0, a.compareTo(b));
    }

    @Test // 6_3
    public void testHigherVersionComparison() {
        Version a = new Version("1.1.2");
        Version b = new Version("1.1.1");
        Assert.assertEquals(1, a.compareTo(b));
    }

    @Test // 6_3
    public void testLowerVersionComparison() {
        Version a = new Version("1.1.1");
        Version b = new Version("1.1.2");
        Assert.assertEquals(-1, a.compareTo(b));
    }
}
