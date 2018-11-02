package com.scylla.core.actor

trait AkkaCustomExceptions {
  class RestartException extends Exception
  class StopException extends Exception
}
