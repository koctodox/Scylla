package com.scylla.cores.actor

trait AkkaCustomExceptions {
  class RestartException extends Exception
  class StopException extends Exception
}
