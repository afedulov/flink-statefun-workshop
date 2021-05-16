################################################################################
# Licensed to Ververica GmbH under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
################################################################################

from ratelimiter import RateLimiter
import time

class Throttler(object):
    """
    A simple implementation of a throttler
    """

    def __init__(self, max_rate_per_second):
        if max_rate_per_second <= 0:
            raise ValueError('max_rate_per_second must be > 0')
        self.min_interval = 1.0 / float(max_rate_per_second)
        self.last_time_called = [0.0]


    def throttle(self):
        elapsed = time.time() - self.last_time_called[0]
        leftToWait = self.min_interval - elapsed
        if leftToWait>0:
            time.sleep(leftToWait)
        self.last_time_called[0] = time.time()


if __name__ == "__main__":
    rate_limiter = Throttler(max_rate_per_second=10000)
    for i in range(1,10000000):
        rate_limiter.throttle()
        print(i)