<header class="topbar">
	<nav class="navbar top-navbar navbar-expand-md navbar-dark">
		<!-- ============================================================== -->
		<!-- Logo -->
		<!-- ============================================================== -->
		<div class="navbar-header">
			<a class="navbar-brand" href="/neurolab/index"> <span
				class="hidden-xs"><span class="font-bold">ADMIN</span></span>
			</a>
		</div>
		<!-- ============================================================== -->
		<!-- End Logo -->
		<!-- ============================================================== -->
		<div class="navbar-collapse">
			<!-- ============================================================== -->
			<!-- toggle and nav items -->
			<!-- ============================================================== -->
			<ul class="navbar-nav mr-auto">
				<!-- This is  -->
				<li class="nav-item"><a
					class="nav-link nav-toggler d-block d-sm-none waves-effect waves-dark"
					href="javascript:void(0)"><i class="ti-menu"></i></a></li>
				<li class="nav-item"><a
					class="nav-link sidebartoggler d-none d-lg-block d-md-block waves-effect waves-dark"
					href="javascript:void(0)"><i class="icon-menu"></i></a></li>


			</ul>
			<!-- ============================================================== -->
			<!-- User profile and search -->
			<!-- ============================================================== -->
			<ul class="navbar-nav my-lg-0">

				<li class="nav-item dropdown" onclick="getColaCitas();"><a
					class="nav-link dropdown-toggle waves-effect waves-dark" href=""
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="ti-email" style="font-size: 30px;"></i>
						<div id="notify-cola">
							<span class="heartbit"></span> <span class="point"></span>
						</div>
				</a>
					<div
						class="dropdown-menu dropdown-menu-right mailbox animated bounceInDown"
						id="colaCita"></div></li>

				<li class="nav-item dropdown" onclick="getColaExamenes();"><a
					class="nav-link dropdown-toggle waves-effect waves-dark" href=""
					id="2" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <i class="icon-note"
						style="font-size: 30px;"></i>
						<div id="notify-examen">
							<span class="heartbit"></span> <span class="point"></span>
						</div>
				</a>
					<div
						class="dropdown-menu mailbox dropdown-menu-right animated bounceInDown"
						aria-labelledby="2" id="colaExamen"></div></li>

				<li class="nav-item right-side-toggle"><a
					class="nav-link  waves-effect waves-light"
					href="javascript:void(0)"><i class="ti-settings"></i></a></li>
			</ul>
		</div>
	</nav>
</header>