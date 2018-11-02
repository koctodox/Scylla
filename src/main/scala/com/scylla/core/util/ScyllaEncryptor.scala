package com.scylla.core.util
//From : https://gist.github.com/jeffsteinmetz/063bd3237033f3af2ed9

import java.security.{MessageDigest, SecureRandom}
import java.util.UUID

case class ScyllaEncryptor() {

  private val TOKEN_LENGTH = 45
  private val TOKEN_CHARS = "PQRST04~56789ABCDvwxEFGHIJK-!@LMNOPQ123RSTpqrUVWXYZabcdefghij123klmnopqrstuvPQRwxyz.>"
  private val secureRandom = new SecureRandom()

  private def toHex(bytes: Array[Byte]): String = bytes.map("%02x".format(_)).mkString("")
  private def sha(s: String): String = toHex(MessageDigest.getInstance("SHA-256").digest(s.getBytes("UTF-8")))
  private def md5(s: String): String = toHex(MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8")))

  private def generateToken(tokenLength: Int): String = {
    val charLen = TOKEN_CHARS.length()
    def generateTokenAccumulator(accumulator: String, number: Int): String = {
      if (number == 0)
        accumulator
      else
        generateTokenAccumulator(accumulator + TOKEN_CHARS(secureRandom.nextInt(charLen)).toString, number - 1)
    }
    generateTokenAccumulator(accumulator = "", number = tokenLength)
  }
  private def generateMD5Token(tokenprefix: String, tokenLength: Int): String = md5(tokenprefix + System.nanoTime() + generateToken(tokenLength))
  private def generateSHAToken(tokenprefix: String, tokenLength: Int): String = sha(tokenprefix + System.nanoTime() + generateToken(tokenLength))

  def generateRandomJWTSecretKey: String = generateMD5Token(UUID.randomUUID().toString.take(5), tokenLength = 20)
  def generateHashPass(password: String): String = sha(password)
  def generateMailToken(email: String): String = generateMD5Token(email, 128)
  def generateAPIKey(userId: UUID, packageId: UUID): String = generateSHAToken(s"${packageId.toString}${userId.toString}", 128)

}
